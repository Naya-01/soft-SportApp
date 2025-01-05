package controllers.factories;

import models.domains.MediaDTO;
import models.domains.CardioDTO;
import models.domains.ExerciceDTO;
import models.domains.FlexibilityDTO;
import models.domains.StrengthDTO;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

public class ExerciceFactory {

    public static ExerciceDTO createExercice(ExerciceDTO exerciceDTO, Object... extraParams) {
        switch (exerciceDTO.getType()) {
            case CARDIO:
                int duration = (int) extraParams[0];
                int distance = (int) extraParams[1];
                return new CardioDTO(UUID.randomUUID(), exerciceDTO.getName(), exerciceDTO.getType(), exerciceDTO.getExplanation(),
                        exerciceDTO.getMedias(), exerciceDTO.getDifficulty(), exerciceDTO.isCustom(), duration, distance);
            case STRENGTH:
                int repetitions = (int) extraParams[0];
                int series = (int) extraParams[1];
                return new StrengthDTO(UUID.randomUUID(), exerciceDTO.getName(), exerciceDTO.getType(), exerciceDTO.getExplanation(),
                        exerciceDTO.getMedias(), exerciceDTO.getDifficulty(), exerciceDTO.isCustom(), repetitions, series);
            case FLEXIBILITY:
                return new FlexibilityDTO(UUID.randomUUID(), exerciceDTO.getName(), exerciceDTO.getType(), exerciceDTO.getExplanation(),
                        exerciceDTO.getMedias(), exerciceDTO.getDifficulty(), exerciceDTO.isCustom());
            default:
                throw new IllegalArgumentException("Invalid exercise type");
        }
    }
}
