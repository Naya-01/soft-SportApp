package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;

public class Strength extends Exercice {

    private int repetitions;
    private int series;

    public Strength(int id, String name, ExerciceType type, String explanation, List<Media> medias, String performance, Difficulty difficulty, int repetitions, int series) {
        super(id, name, type, explanation, medias, performance, difficulty);
        this.repetitions = repetitions;
        this.series = series;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getSeries() {
        return series;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
