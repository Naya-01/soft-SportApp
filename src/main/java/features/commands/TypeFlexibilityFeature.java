package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY;
import static features.FeaturesEnum.TYPE;

import features.AbstractFeature;
import features.ConstraintType;

public class TypeFlexibilityFeature extends AbstractFeature {

    public TypeFlexibilityFeature() {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), true, true, ConstraintType.OR, TYPE.getFeature(), TYPE.getFeature());
    }

    public TypeFlexibilityFeature(boolean active) {
        super(EXERCICE_TYPE_FLEXIBILITY.getFeature(), active, true, ConstraintType.OR, TYPE.getFeature(), TYPE.getFeature());
    }
}