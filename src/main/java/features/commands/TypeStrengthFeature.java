package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_STRENGTH;

import features.AbstractFeature;
import features.ConstraintType;

public class TypeStrengthFeature extends AbstractFeature {

    public TypeStrengthFeature() {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), true, true, ConstraintType.OR, "exercise_type", "type");
    }

    public TypeStrengthFeature(boolean active) {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), active, true, ConstraintType.OR, "exercise_type", "type");
    }
}
