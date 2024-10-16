package models.domains;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import models.exercices.Exercice;

import java.util.List;
import java.util.UUID;

public class StrengthDTO extends ExerciceDTO {

    private int repetitions;
    private int series;

    public StrengthDTO() {
        super.setType(ExerciceType.STRENGTH);
    }

    public StrengthDTO(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, int repetitions, int series) {
        super(id, name, type, explanation, medias, difficulty, isCustom);
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
