package controllers.factories;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import models.exercices.Cardio;
import models.exercices.Exercice;
import models.exercices.Flexibility;
import models.exercices.Strength;

import java.util.List;
import java.util.UUID;

public class ExerciceFactory {

    public static Exercice createExercice(ExerciceType type, String name, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {
        switch (type) {
            case CARDIO:
                int duration = (int) extraParams[0];
                int distance = (int) extraParams[1];
                return new Cardio(UUID.randomUUID(), name, type, explanation, medias, difficulty, isCustom, duration, distance);
            case STRENGTH:
                int repetitions = (int) extraParams[0];
                int series = (int) extraParams[1];
                return new Strength(UUID.randomUUID(), name, type, explanation, medias, difficulty, isCustom, repetitions, series);
            case FLEXIBILITY:
                return new Flexibility(UUID.randomUUID(), name, type, explanation, medias, difficulty, isCustom);
            default:
                throw new IllegalArgumentException("Invalid exercise type");
        }
    }
}
