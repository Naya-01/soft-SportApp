package features;

public enum FeaturesEnum {

    COMMUNITY("community"),
    EXERCISE("exercise"),
    PREMIUM("premium"),
    PROFILE("profile"),
    PAYMENT_METHODS("payment_methods"),
    TYPE("type"),
    EXERCICE_CUSTOM_ADD("exercice_custom_add"),
    EXERCICE_CUSTOM_LIST("exercice_custom_list"),
    EXERCICE_DIFFICULTY_BEGINNER("difficulty_beginner"),
    EXERCICE_DIFFICULTY_INTERMEDIATE("difficulty_intermediate"),
    EXERCICE_DIFFICULTY_ADVANCED("difficulty_advanced"),
    EXERCICE_TYPE_CARDIO("type_cardio"),
    EXERCICE_TYPE_STRENGTH("type_strength"),
    EXERCICE_TYPE_FLEXIBILITY("type_flexibility"),
    EXERCICE_MEDIA("media"),
    MEDIA_IMAGE("media_image"),
    MEDIA_VIDEO("media_video"),
    PERFORMANCE("performance"),
    EXERCICE_TIMER("timer"),
    PAYMENT_METHOD_BANCONTACT("bancontact"),
    PAYMENT_METHOD_PAYPAL("paypal");

    private String feature;

    FeaturesEnum(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return this.feature;
    }
}
