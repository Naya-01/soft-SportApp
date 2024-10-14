package models.PaymentMethod;

public class Paypal implements PaymentMethod {

    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Paypal");
    }

    public String getName() {
        return "Paypal";
    }
}
