package features.commands.authentication;

import static features.FeaturesEnum.AUTHENTICATION;

import features.AbstractFeature;
import features.ConstraintType;
import features.FeaturesEnum;

public class AuthenticationFeature extends AbstractFeature {

        public AuthenticationFeature() {
            super(AUTHENTICATION.getFeature(), true, ConstraintType.MANDATORY, null);
        }

        public AuthenticationFeature(boolean active) {
            super(AUTHENTICATION.getFeature(), active, ConstraintType.MANDATORY,  null);
        }
}
