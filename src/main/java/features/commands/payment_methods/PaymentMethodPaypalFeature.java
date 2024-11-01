package features.commands.payment_methods;

import static features.FeaturesEnum.PAYMENT_METHODS;
import static features.FeaturesEnum.PAYMENT_METHOD_PAYPAL;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethodPaypalFeature extends AbstractFeature {

    public PaymentMethodPaypalFeature() {
        super(PAYMENT_METHOD_PAYPAL.getFeature(), true, ConstraintType.OR, PAYMENT_METHODS.getFeature());
    }
    public PaymentMethodPaypalFeature(boolean active) {
        super(PAYMENT_METHOD_PAYPAL.getFeature(), active, ConstraintType.OR, PAYMENT_METHODS.getFeature());
    }
}
