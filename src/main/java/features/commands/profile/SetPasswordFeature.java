package features.commands.profile;

import features.AbstractFeature;
import features.ConstraintType;

import static features.FeaturesEnum.SET_PASSWORD;

public class SetPasswordFeature extends AbstractFeature {

    public SetPasswordFeature() {
        super(SET_PASSWORD.getFeature(), true, ConstraintType.MANDATORY, null);
    }
    public SetPasswordFeature(boolean active) {
        super(SET_PASSWORD.getFeature(), active,  ConstraintType.MANDATORY, null);
    }

}
