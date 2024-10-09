package models.PaymentMethod;

public interface PaymentMethod {

    public void pay(double amount);

    public String getName();
}
