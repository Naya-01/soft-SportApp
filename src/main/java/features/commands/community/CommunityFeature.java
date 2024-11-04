package features.commands.community;

import static features.FeaturesEnum.COMMUNITY;

import features.AbstractFeature;
import features.ConstraintType;

public class CommunityFeature extends AbstractFeature {

    public CommunityFeature() {
        super(COMMUNITY.getFeature(), true,  ConstraintType.OPTIONAL, null);
    }
    public CommunityFeature(boolean active) {
        super(COMMUNITY.getFeature(), active, ConstraintType.OPTIONAL, null);
    }

}
