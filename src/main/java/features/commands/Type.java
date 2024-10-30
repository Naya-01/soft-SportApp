package features.commands;

import static features.FeaturesEnum.TYPE;

import features.AbstractFeature;
import features.ConstraintType;

public class Type extends AbstractFeature {

    public Type() {
        super(TYPE.getFeature(), true, true,  ConstraintType.MANDATORY, TYPE.getFeature(), "exercise");
    }

    public Type(boolean active) {
        super(TYPE.getFeature(), active, true,  ConstraintType.MANDATORY, TYPE.getFeature(), "exercise");
    }

}
