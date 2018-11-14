package es.vlctesting.lite.rci;

import org.sonar.api.Plugin;

import es.vlctesting.lite.rci.ws.RciWebService;

public final class RciPlugin implements Plugin {
	
	@Override
	public void define(final Context context) {
		context.addExtensions(RciProperties.definitions());
		context.addExtension(RciMetrics.class);
		context.addExtension(RciMeasureComputer.class);
		context.addExtension(RciWebService.class);
		context.addExtension(RciPageDefinition.class);
	}
}