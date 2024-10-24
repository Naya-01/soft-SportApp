package models.enums;

public enum PaymentMethodEnum {

    PAYPAL("Paypal"),
    BANCONTACT("Bancontact");

    private String name;

    PaymentMethodEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
