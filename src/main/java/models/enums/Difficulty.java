package models.enums;

import features.FeaturesEnum;

public enum Difficulty {

    BEGINNER("Beginner", FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER.getFeature()),
    INTERMEDIATE("Intermediate", FeaturesEnum.EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature()),
    ADVANCED("Advanced", FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED.getFeature());

    private String difficulty;
    private String featureName;

    Difficulty(String difficulty, String feature) {
        this.difficulty = difficulty;
        this.featureName = feature;
    }

    public String getDifficulty() {
            return difficulty;
        }

    public String getFeatureName() {
        return featureName;
    }
}
