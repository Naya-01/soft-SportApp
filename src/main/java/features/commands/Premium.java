package features.commands;

import static features.FeaturesEnum.PREMIUM;

import features.AbstractFeature;
import features.ConstraintType;

public class Premium extends AbstractFeature {

    public Premium() {
        super(PREMIUM.getFeature(), true, false, ConstraintType.OPTIONAL, "premium", null);
    }
    public Premium(boolean active) {
        super(PREMIUM.getFeature(), active, false, ConstraintType.OPTIONAL, "premium", null);
    }

}