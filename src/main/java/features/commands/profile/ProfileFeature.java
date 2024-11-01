package features.commands.profile;

import static features.FeaturesEnum.PROFILE;

import features.AbstractFeature;
import features.ConstraintType;

public class ProfileFeature extends AbstractFeature {

    public ProfileFeature() {
        super(PROFILE.getFeature(), true, ConstraintType.MANDATORY, null);
    }
    public ProfileFeature(boolean active) {
        super(PROFILE.getFeature(), active,  ConstraintType.MANDATORY, null);
    }

}
