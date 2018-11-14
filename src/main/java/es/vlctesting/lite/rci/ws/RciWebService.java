package es.vlctesting.lite.rci.ws;

import org.sonar.api.config.Configuration;
import org.sonar.api.server.ws.WebService;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import es.vlctesting.lite.rci.ws.handlers.RciRenderPageHandler;

public class RciWebService implements WebService {

	private static final Logger LOGGER = Loggers.get(RciWebService.class);

	private final Configuration configuration;

	public RciWebService(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void define(Context context) {
		LOGGER.debug("Create controller to Rci Plugin");

		final NewController controller = context.createController("api/rci");
		controller.setDescription("Rules Compliance Index Web Service");
		defineRciPage(controller);
		controller.done();
	}

	private void defineRciPage(final NewController controller) {
		LOGGER.debug("Create action to render Rci Page");

		NewAction action = controller.createAction("rciPage").setDescription("Renders Rules Compliance Index Page")
				.setSince("1.0").setHandler(new RciRenderPageHandler(this.configuration));

		action.createParam("key").setDescription("Component key").setRequired(true).setExampleValue("my_project");
	}

}
