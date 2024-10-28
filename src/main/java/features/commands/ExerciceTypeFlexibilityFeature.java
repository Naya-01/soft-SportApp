package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceTypeFlexibilityFeature extends AbstractFeature {

    public ExerciceTypeFlexibilityFeature() {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), true, true, ConstraintType.OR, "exercise_type", "type");
    }

    public ExerciceTypeFlexibilityFeature(boolean active) {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), active, true, ConstraintType.OR, "exercise_type", "type");
    }
}