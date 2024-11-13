package features.commands.profile;

import features.AbstractFeature;
import features.ConstraintType;

import static features.FeaturesEnum.SET_USERNAME;

public class SetUsernameFeature extends AbstractFeature {

    public SetUsernameFeature() {
        super(SET_USERNAME.getFeature(), true, ConstraintType.OPTIONAL, null);
    }
    public SetUsernameFeature(boolean active) {
        super(SET_USERNAME.getFeature(), active,  ConstraintType.MANDATORY, null);
    }
}
