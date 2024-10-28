package features.commands;

import static features.FeaturesEnum.EXERCICE_PERFORMANCE;

import features.AbstractFeature;
import features.ConstraintType;

public class ExercicePerformanceFeature extends AbstractFeature {

    public ExercicePerformanceFeature() {
        super(EXERCICE_PERFORMANCE.getFeature(), true, false, ConstraintType.OPTIONAL, "performance", "exercise");
    }
    public ExercicePerformanceFeature(boolean active) {
        super(EXERCICE_PERFORMANCE.getFeature(), active, false, ConstraintType.OPTIONAL, "performance", "exercise");
    }
}
