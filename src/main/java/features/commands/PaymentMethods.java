package features.commands;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethods extends AbstractFeature {

        public PaymentMethods() {
            super("payment_methods", true, false, ConstraintType.OPTIONAL, "premium", "premium");
        }
        public PaymentMethods(boolean active) {
            super("payment_methods", active, false, ConstraintType.OPTIONAL, "premium", "premium");
        }

}
