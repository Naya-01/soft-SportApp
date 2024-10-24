package features;

public enum FeaturesEnum {

    EXERCICE_CUSTOM_ADD("exercice_custom_add"),
    EXERCICE_CUSTOM_LIST("exercice_custom_list"),
    EXERCICE_DIFFICULTY_BEGINNER("exercice_difficulty_beginner"),
    EXERCICE_DIFFICULTY_INTERMEDIATE("exercice_difficulty_intermediate"),
    EXERCICE_DIFFICULTY_ADVANCED("exercice_difficulty_advanced"),
    EXERCICE_TYPE_CARDIO("exercice_type_cardio"),
    EXERCICE_TYPE_STRENGTH("exercice_type_strength"),
    EXERCICE_TYPE_FLEXIBILITY("exercice_type_flexibility"),
    EXERCICE_MEDIA("exercice_media"),
    EXERCICE_MEDIA_IMAGE("exercice_media_image"),
    EXERCICE_MEDIA_VIDEO("exercice_media_video"),
    EXERCICE_PERFORMANCE("exercice_performance"),
    EXERCICE_TIMER("exercice_timer"),
    PAYMENT_METHOD_BANCONTACT("payment_method_bancontact"),
    PAYMENT_METHOD_PAYPAL("payment_method_paypal");

    private String feature;

    FeaturesEnum(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return this.feature;
    }
}
