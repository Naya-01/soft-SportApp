package controllers;

import features.managers.FeatureManager;
import features.FeaturesEnum;
import java.util.ArrayList;
import java.util.List;
import models.PaymentMethod.Bancontact;
import models.PaymentMethod.PaymentMethod;
import models.PaymentMethod.Paypal;
import models.User;
import models.enums.ExerciceType;
import models.enums.PaymentMethodEnum;

public class PaymentMethodController {
    private PaymentMethod paymentMethod;
    private User userModel;

    private FeatureManager featureManager;

    public PaymentMethodController() {
        this.userModel = new User();
        this.featureManager = FeatureManager.getInstance();
    }

    public boolean upgradeAccount(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
        this.paymentMethod.pay(100);
        return userModel.upgradeAccount();
    }

    public List<PaymentMethod> getAvailablePaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        if (featureManager.isActive(FeaturesEnum.PAYMENT_METHOD_PAYPAL.getFeature())) methods.add(new Paypal());
        if (featureManager.isActive(FeaturesEnum.PAYMENT_METHOD_BANCONTACT.getFeature())) methods.add(new Bancontact());
        return methods;
    }
}
