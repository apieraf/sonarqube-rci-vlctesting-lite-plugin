package es.vlctesting.lite.rci;

import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import es.vlctesting.lite.rci.utils.RciRatings;
import es.vlctesting.lite.rci.utils.RciUtils;
import es.vlctesting.lite.rci.utils.RciWeights;

public class RciMeasureComputer implements MeasureComputer {

	private static final Logger LOGGER = Loggers.get(RciMeasureComputer.class);

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOGGER.debug("Building Rci MeasureComputerDefinition...  ");

		return defContext.newDefinitionBuilder()
				.setInputMetrics(RciUtils.METRIC_BLOCKER_VIOLATIONS, RciUtils.METRIC_CRITICAL_VIOLATIONS,
						RciUtils.METRIC_MAJOR_VIOLATIONS, RciUtils.METRIC_MINOR_VIOLATIONS,
						RciUtils.METRIC_INFO_VIOLATIONS, RciUtils.METRIC_LINES_OF_CODE)
				.setOutputMetrics(RciUtils.RULES_COMPLIANCE_INDEX_KEY, RciUtils.RULES_COMPLIANCE_RATING_KEY).build();
	}

	@Override
	public void compute(MeasureComputerContext context) {

		LOGGER.debug("Running Rci Plugin...");
		
		int blockerIssues = RciUtils.getMeasureValue(context, RciUtils.METRIC_BLOCKER_VIOLATIONS);
		int criticalIssues = RciUtils.getMeasureValue(context, RciUtils.METRIC_CRITICAL_VIOLATIONS);
		int majorIssues = RciUtils.getMeasureValue(context, RciUtils.METRIC_MAJOR_VIOLATIONS);
		int minorIssues = RciUtils.getMeasureValue(context, RciUtils.METRIC_MINOR_VIOLATIONS);
		int infoIssues = RciUtils.getMeasureValue(context, RciUtils.METRIC_INFO_VIOLATIONS);

		int linesCode = RciUtils.getMeasureValue(context, RciUtils.METRIC_LINES_OF_CODE);

		RciWeights weights = RciProperties.getWights(context.getSettings());
		RciRatings rating = RciProperties.getRating(context.getSettings());
		
		int issuesWeight = weights.getBlocker() * blockerIssues + weights.getCritical() * criticalIssues
				+ weights.getMajor() * majorIssues + weights.getMinor() * minorIssues + weights.getInfo() * infoIssues;
		
		LOGGER.trace("Rci Issues Weight value: " + issuesWeight);
		LOGGER.trace("Rci Lines Of Code value: " + linesCode);

		double rulesComplianceIndex;

		if (linesCode > 0) {
			rulesComplianceIndex = (1.0 - (double) issuesWeight / (double) linesCode) * 100;

			context.addMeasure(RciMetrics.RULES_COMPLIANCE_INDEX.key(), Math.max(rulesComplianceIndex, 0.0));

			context.addMeasure(RciMetrics.RULES_COMPLIANCE_RATING.key(), rating.getRating(rulesComplianceIndex));
		}

	}

}
