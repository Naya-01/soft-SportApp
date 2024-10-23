package controllers;

import models.PaymentMethod.PaymentMethod;

public class PaymentMethodController {
    private PaymentMethod paymentMethod;

    public PaymentMethodController(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
