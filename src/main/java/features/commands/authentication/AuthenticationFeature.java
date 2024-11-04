package features.commands.authentication;

import features.AbstractFeature;
import features.ConstraintType;

public class AuthenticationFeature extends AbstractFeature {

        public AuthenticationFeature() {
            super("authentication", true, ConstraintType.MANDATORY, null);
        }

        public AuthenticationFeature(boolean active) {
            super("authentication", active, ConstraintType.MANDATORY,  null);
        }
}
