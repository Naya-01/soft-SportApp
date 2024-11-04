package features.commands.payment_methods;

import static features.FeaturesEnum.PAYMENT_METHODS;
import static features.FeaturesEnum.PREMIUM;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethods extends AbstractFeature {

        public PaymentMethods() {
            super(PAYMENT_METHODS.getFeature(), true,  ConstraintType.OPTIONAL, PREMIUM.getFeature());
        }
        public PaymentMethods(boolean active) {
            super(PAYMENT_METHODS.getFeature(), active,  ConstraintType.OPTIONAL, PREMIUM.getFeature());
        }

}
