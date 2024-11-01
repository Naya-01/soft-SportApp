package features.commands.exercise.type;

import static features.FeaturesEnum.EXERCISE;
import static features.FeaturesEnum.TYPE;

import features.AbstractFeature;
import features.ConstraintType;

public class Type extends AbstractFeature {

    public Type() {
        super(TYPE.getFeature(), true,  ConstraintType.MANDATORY, EXERCISE.getFeature());
    }

    public Type(boolean active) {
        super(TYPE.getFeature(), active, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }

}
