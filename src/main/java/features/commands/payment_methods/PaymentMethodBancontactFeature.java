package features.commands.payment_methods;

import static features.FeaturesEnum.PAYMENT_METHODS;
import static features.FeaturesEnum.PAYMENT_METHOD_BANCONTACT;

import features.AbstractFeature;
import features.ConstraintType;

public class PaymentMethodBancontactFeature extends AbstractFeature {

    public PaymentMethodBancontactFeature() {
        super(PAYMENT_METHOD_BANCONTACT.getFeature(), true, ConstraintType.OR, PAYMENT_METHODS.getFeature());
    }
    public PaymentMethodBancontactFeature(boolean active) {
        super(PAYMENT_METHOD_BANCONTACT.getFeature(), active, ConstraintType.OR, PAYMENT_METHODS.getFeature());
    }
}
