package models.PaymentMethod;

public class Bancontact implements PaymentMethod {

    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Bancontact");
    }
}
