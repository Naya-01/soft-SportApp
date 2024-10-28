package features.commands;

import static features.FeaturesEnum.COMMUNITY;

import features.AbstractFeature;
import features.ConstraintType;

public class CommunityFeature extends AbstractFeature {

    public CommunityFeature() {
        super(COMMUNITY.getFeature(), true, false, ConstraintType.OPTIONAL, "community", null);
    }
    public CommunityFeature(boolean active) {
        super(COMMUNITY.getFeature(), active, false, ConstraintType.OPTIONAL, "community", null);
    }

}
