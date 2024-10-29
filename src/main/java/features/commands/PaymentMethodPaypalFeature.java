package features.commands;

import static features.FeaturesEnum.PAYMENT_METHOD_PAYPAL;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethodPaypalFeature extends AbstractFeature {

    public PaymentMethodPaypalFeature() {
        super(PAYMENT_METHOD_PAYPAL.getFeature(), true, false, ConstraintType.OR, "payment_methods", "payment_methods");
    }
    public PaymentMethodPaypalFeature(boolean active) {
        super(PAYMENT_METHOD_PAYPAL.getFeature(), active, false, ConstraintType.OR, "payment_methods", "payment_methods");
    }
}
