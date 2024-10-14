package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

public class Flexibility extends Exercice {

    public Flexibility() {
        super.setType(ExerciceType.FLEXIBILITY);
    }

    public Flexibility(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom) {
        super(id, name, type, explanation, medias, difficulty, isCustom);
    }
}
