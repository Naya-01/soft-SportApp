package models.domains;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

public class CardioDTO extends ExerciceDTO {

    private int duration;
    private int distance;

    public CardioDTO() {
        super.setType(ExerciceType.CARDIO);
    }

    public CardioDTO(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, int duration, int distance) {
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
