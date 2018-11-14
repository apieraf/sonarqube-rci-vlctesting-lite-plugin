package es.vlctesting.lite.rci.ws.handlers;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.sonar.api.config.Configuration;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.Request.StringParam;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;
import org.sonarqube.ws.WsMeasures.ComponentWsResponse;
import org.sonarqube.ws.WsMeasures.Measure;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;
import org.sonarqube.ws.client.measure.ComponentWsRequest;

import es.vlctesting.lite.rci.utils.RciUtils;

public class RciRenderPageHandler implements RequestHandler {

	private final Configuration configuration;

	public RciRenderPageHandler(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		String html = "";

		StringParam componentKey = request.getParam("key");

		final WsClient client = WsClientFactories.getLocal().newClient(request.localConnector());

		ComponentWsRequest componentWsRequest = new ComponentWsRequest().setComponent(componentKey.getValue())
				.setMetricKeys(new ArrayList<>(Arrays.asList(RciUtils.RULES_COMPLIANCE_INDEX_KEY)));

		ComponentWsResponse componentWsResponse = client.measures().component(componentWsRequest);

		Measure rulesComplianceIndex = componentWsResponse.getComponent().getMeasures(0);

		html = renderRciTemplate("/static/templates/rciPageTemplate.vm", rulesComplianceIndex.getValue());
		response.stream().output().write(html.getBytes());
	}

	private String renderRciTemplate(String template, String rulesComplianceIndexValue) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("resource.loader", "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();

		Template t = ve.getTemplate(template, "UTF-8");
		t.setEncoding("UTF-8");

		VelocityContext vc = new VelocityContext();

		ResourceBundle messages = ResourceBundle.getBundle("org.sonar.l10n.rci", Locale.getDefault());
		vc.put("messages", messages);

		String ratingsConfiguration = configuration.get(RciUtils.RATINGS_KEY).orElse(RciUtils.RATINGS_DEFAULT_VALUE);
		String[] ratings = ratingsConfiguration.split(",");

		int wellValue = RciUtils.getIntFromArray(ratings, 0);
		int normalValue = RciUtils.getIntFromArray(ratings, 1);
		int poorlyValue = RciUtils.getIntFromArray(ratings, 2);
		int unreasonableValue = RciUtils.getIntFromArray(ratings, 3);

		// RULES COMPLIANCE
		vc.put("aValue", wellValue);
		vc.put("bValue", normalValue);
		vc.put("cValue", poorlyValue);
		vc.put("dValue", unreasonableValue);
		vc.put("rulesCompliance_measure", rulesComplianceIndexValue);

		StringWriter sw = new StringWriter();
		t.merge(vc, sw);

		return sw.toString();
	}

}
