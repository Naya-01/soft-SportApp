package features.commands;

import static features.FeaturesEnum.PROFILE;

import features.AbstractFeature;
import features.ConstraintType;

public class ProfileFeature extends AbstractFeature {

    public ProfileFeature() {
        super(PROFILE.getFeature(), true, true, ConstraintType.MANDATORY, "profile", null);
    }
    public ProfileFeature(boolean active) {
        super(PROFILE.getFeature(), active, true, ConstraintType.MANDATORY, "profile", null);
    }

}
