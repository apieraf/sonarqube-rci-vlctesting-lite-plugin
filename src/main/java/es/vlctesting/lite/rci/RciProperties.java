package es.vlctesting.lite.rci;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.ce.measure.Settings;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import es.vlctesting.lite.rci.utils.RciRatings;
import es.vlctesting.lite.rci.utils.RciUtils;
import es.vlctesting.lite.rci.utils.RciWeights;

public final class RciProperties {

	private RciProperties() {
		throw new IllegalStateException("Utility class");
	}

	public static List<PropertyDefinition> definitions() {
		return Arrays.asList(
				PropertyDefinition.builder(RciUtils.SEVERITY_WEIGHTS_KEY).name("Issue weights")
						.description("Relative weiths of issues based on severity (blocker,critical,major,minor,info)")
						.category(RciUtils.CATEGORY).defaultValue(RciUtils.SEVERITY_WEIGHTS_DEFAULT_VALUE).index(100)
						.onQualifiers(Qualifiers.PROJECT).build(),
				PropertyDefinition.builder(RciUtils.RATINGS_KEY).name("Rating").description(
						"Rating (ranging from A (very good) to E (very bad)). This setting define the values for A through D with E being below the last value.")
						.category(RciUtils.CATEGORY).defaultValue(RciUtils.RATINGS_DEFAULT_VALUE).index(200)
						.onQualifiers(Qualifiers.PROJECT).build());
	}

	public static RciWeights getWights(final Settings settings) {
		String weightsSettings = settings.getString(RciUtils.SEVERITY_WEIGHTS_KEY);

		String[] weights = weightsSettings == null ? RciUtils.SEVERITY_WEIGHTS_DEFAULT_VALUE.split(",")
				: weightsSettings.split(",");

		return new RciWeights(RciUtils.getIntFromArray(weights, 0), RciUtils.getIntFromArray(weights, 1),
				RciUtils.getIntFromArray(weights, 2), RciUtils.getIntFromArray(weights, 3),
				RciUtils.getIntFromArray(weights, 4));
	}

	public static RciRatings getRating(final Settings settings) {
		String ratingsSettings = settings.getString(RciUtils.RATINGS_KEY);

		String[] ratings = ratingsSettings == null ? RciUtils.RATINGS_DEFAULT_VALUE.split(",")
				: ratingsSettings.split(",");

		return new RciRatings(RciUtils.getIntFromArray(ratings, 0), RciUtils.getIntFromArray(ratings, 1),
				RciUtils.getIntFromArray(ratings, 2), RciUtils.getIntFromArray(ratings, 3));
	}

}
