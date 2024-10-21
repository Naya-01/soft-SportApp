package models.domains;

import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

public class FlexibilityDTO extends ExerciceDTO {

    public FlexibilityDTO() {
        super.setType(ExerciceType.FLEXIBILITY);
    }

    public FlexibilityDTO(UUID id, String name, ExerciceType type, String explanation, List<MediaDTO> medias, Difficulty difficulty, boolean isCustom) {
        super(id, name, type, explanation, medias, difficulty, isCustom);
    }
}
