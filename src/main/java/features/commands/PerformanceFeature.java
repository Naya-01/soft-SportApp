package features.commands;

import static features.FeaturesEnum.PERFORMANCE;

import features.AbstractFeature;
import features.ConstraintType;

public class PerformanceFeature extends AbstractFeature {

    public PerformanceFeature() {
        super(PERFORMANCE.getFeature(), true, false, ConstraintType.OPTIONAL, "performance", "exercise");
    }
    public PerformanceFeature(boolean active) {
        super(PERFORMANCE.getFeature(), active, false, ConstraintType.OPTIONAL, "performance", "exercise");
    }
}
