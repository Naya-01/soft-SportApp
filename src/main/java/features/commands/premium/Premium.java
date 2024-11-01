package features.commands.premium;

import static features.FeaturesEnum.PREMIUM;

import features.AbstractFeature;
import features.ConstraintType;

public class Premium extends AbstractFeature {

    public Premium() {
        super(PREMIUM.getFeature(), true, ConstraintType.OPTIONAL,  null);
    }
    public Premium(boolean active) {
        super(PREMIUM.getFeature(), active, ConstraintType.OPTIONAL, null);
    }

}
