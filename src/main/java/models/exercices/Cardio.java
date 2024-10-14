package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;

public class Cardio extends Exercice {

    private int duration;
    private int distance;

    public Cardio(int id, String name, ExerciceType type, String explanation, List<Media> medias, String performance, Difficulty difficulty, int duration, int distance) {
        super(id, name, type, explanation, medias, performance, difficulty);
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
