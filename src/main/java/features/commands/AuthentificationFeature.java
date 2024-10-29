package features.commands;

import features.AbstractFeature;
import features.ConstraintType;

public class AuthentificationFeature extends AbstractFeature {

        public AuthentificationFeature() {
            super("authentification", true, true, ConstraintType.MANDATORY, "authentification", null);
        }

        public AuthentificationFeature(boolean active) {
            super("authentification", active, true, ConstraintType.MANDATORY, "authentification", null);
        }
}
