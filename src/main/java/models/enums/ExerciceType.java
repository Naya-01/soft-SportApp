package models.enums;

import features.FeaturesEnum;

public enum ExerciceType {
    CARDIO("Cardio", FeaturesEnum.EXERCICE_TYPE_CARDIO.getFeature()),
    STRENGTH("Strength", FeaturesEnum.EXERCICE_TYPE_STRENGTH.getFeature()),
    FLEXIBILITY("Flexibility", FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY.getFeature());

    private String typeName;
    private String featureName;

    ExerciceType(String typeName, String featureName) {
        this.typeName = typeName;
        this.featureName = featureName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getFeatureName() {
        return featureName;
    }
}
