package features.commands;

import static features.FeaturesEnum.PAYMENT_METHOD_BANCONTACT;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethodBancontactFeature extends AbstractFeature {

    public PaymentMethodBancontactFeature() {
        super(PAYMENT_METHOD_BANCONTACT.getFeature(), true, false, ConstraintType.OR, "premium", "payment_methods");
    }
    public PaymentMethodBancontactFeature(boolean active) {
        super(PAYMENT_METHOD_BANCONTACT.getFeature(), active, false, ConstraintType.OR, "premium", "payment_methods");
    }
}
