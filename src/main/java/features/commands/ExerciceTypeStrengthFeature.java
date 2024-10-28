package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_STRENGTH;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceTypeStrengthFeature extends AbstractFeature {

    public ExerciceTypeStrengthFeature() {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), true, true, ConstraintType.OR, "exercise_type", "type");
    }

    public ExerciceTypeStrengthFeature(boolean active) {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), active, true, ConstraintType.OR, "exercise_type", "type");
    }
}
