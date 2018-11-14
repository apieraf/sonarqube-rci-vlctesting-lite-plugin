package es.vlctesting.lite.rci.utils;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;

public final class RciUtils {
	
	private RciUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static final String DOMAIN = "RCI";
	public static final String CATEGORY = "RCI";

	public static final String RULES_COMPLIANCE_INDEX_KEY = "rules_compliance_index";
	public static final String RULES_COMPLIANCE_RATING_KEY = "rules_compliance_rating";

	public static final String SEVERITY_WEIGHTS_KEY = "sonar.rci.weights";
	public static final String SEVERITY_WEIGHTS_DEFAULT_VALUE = "10,5,3,1,0";
	public static final String RATINGS_KEY = "sonar.rci.ratings";
	public static final String RATINGS_DEFAULT_VALUE = "97,92,85,75";
	
    public static final String METRIC_BLOCKER_VIOLATIONS = CoreMetrics.BLOCKER_VIOLATIONS_KEY;
    public static final String METRIC_CRITICAL_VIOLATIONS = CoreMetrics.CRITICAL_VIOLATIONS_KEY;
    public static final String METRIC_MAJOR_VIOLATIONS = CoreMetrics.MAJOR_VIOLATIONS_KEY;
    public static final String METRIC_MINOR_VIOLATIONS = CoreMetrics.MINOR_VIOLATIONS_KEY;
    public static final String METRIC_INFO_VIOLATIONS = CoreMetrics.INFO_VIOLATIONS_KEY;
    public static final String METRIC_LINES_OF_CODE = CoreMetrics.NCLOC_KEY;
    
    public static int getMeasureValue(final MeasureComputerContext context, final String key) {
        final Measure measure = context.getMeasure(key);
        if (measure == null) {
            return 0;
        } else {
            return measure.getIntValue();
        }
    }
    
	public static int getIntFromArray(String[] strings, int index) {
		if (index >= strings.length || "".equals(strings[index])) {
			return 0;
		} else {
			return Integer.parseInt(strings[index]);
		}
	}

}
