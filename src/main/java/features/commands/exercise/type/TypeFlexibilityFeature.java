package features.commands.exercise.type;

import static features.FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY;
import static features.FeaturesEnum.TYPE;

import features.AbstractFeature;
import features.ConstraintType;

public class TypeFlexibilityFeature extends AbstractFeature {

    public TypeFlexibilityFeature() {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), true, ConstraintType.OR, TYPE.getFeature());
    }

    public TypeFlexibilityFeature(boolean active) {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), active, ConstraintType.OR, TYPE.getFeature());
    }
}