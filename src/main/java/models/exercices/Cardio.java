package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

public class Cardio extends Exercice {

    private int duration;
    private int distance;

    public Cardio() {
        super.setType(ExerciceType.CARDIO);
    }

    public Cardio(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, int duration, int distance) {
        super(id, name, type, explanation, medias, difficulty, isCustom);
        this.duration = duration;
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
