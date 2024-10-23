package utils;

public enum FeaturesEnum {

    EXERCICE_CUSTOM_ADD("exercice-custom-add"),
    EXERCICE_CUSTOM_LIST("exercice-custom-list"),
    EXERCICE_DIFFICULTY_BEGINNER("exercice-difficulty-beginner"),
    EXERCICE_DIFFICULTY_INTERMEDIATE("exercice-difficulty-intermediate"),
    EXERCICE_DIFFICULTY_ADVANCED("exercice-difficulty-advanced"),
    EXERCICE_TYPE_CARDIO("exercice-type-cardio"),
    EXERCICE_TYPE_STRENGTH("exercice-type-strength"),
    EXERCICE_TYPE_FLEXIBILITY("exercice-type-flexibility"),
    EXERCICE_MEDIA("exercice-media"),
    EXERCICE_MEDIA_IMAGE("exercice-media-image"),
    EXERCICE_MEDIA_VIDEO("exercice-media-video"),
    EXERCICE_PERFORMANCE("exercice-performance"),
    EXERCICE_TIMER("exercice-timer"),
    PAYMENT_METHOD_BANCONTACT("payment-method-bancontact"),
    PAYMENT_METHOD_PAYPAL("payment-method-paypal");

    private String feature;

    FeaturesEnum(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return this.feature;
    }
}
