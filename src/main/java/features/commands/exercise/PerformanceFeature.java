package features.commands.exercise;

import static features.FeaturesEnum.EXERCISE;
import static features.FeaturesEnum.PERFORMANCE;

import features.AbstractFeature;
import features.ConstraintType;

public class PerformanceFeature extends AbstractFeature {

    public PerformanceFeature() {
        super(PERFORMANCE.getFeature(), true, ConstraintType.OPTIONAL, EXERCISE.getFeature());
    }
    public PerformanceFeature(boolean active) {
        super(PERFORMANCE.getFeature(), active, ConstraintType.OPTIONAL,EXERCISE.getFeature());
    }
}
