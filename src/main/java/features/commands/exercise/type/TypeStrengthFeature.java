package features.commands.exercise.type;

import static features.FeaturesEnum.EXERCICE_TYPE_STRENGTH;
import static features.FeaturesEnum.TYPE;

import features.AbstractFeature;
import features.ConstraintType;

public class TypeStrengthFeature extends AbstractFeature {

    public TypeStrengthFeature() {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), true, ConstraintType.OR, TYPE.getFeature());
    }

    public TypeStrengthFeature(boolean active) {
        super(EXERCICE_TYPE_STRENGTH.getFeature(), active, ConstraintType.OR, TYPE.getFeature());
    }
}
