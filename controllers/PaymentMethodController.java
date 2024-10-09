package controllers;

import models.PaymentMethod.PaymentMethod;

public class PaymentMethodController implements ControllerInterface {
    private PaymentMethod paymentMethod;

    public PaymentMethodController(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        // Activation logic here
        return 0;
    }

    @Override
    public boolean enableUIView() {
        // Enable logic here
        return true;
    }

    @Override
    public boolean disableUIView() {
        // Disable logic here
        return true;
    }

    @Override
    public String[] getStateAsLog() {
        return new String[]{paymentMethod.getName()};
    }
}
