package models.exercices;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controllers.factories.ExerciceFactory;
import models.Media;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;

public class Exercice {

    private static final String EXERCICE_FILE_PATH = "src/main/java/data/exercices.json";
    private static final String CUSTOM_EXERCICE_FILE_PATH = "src/main/java/data/custom-exercices.json";
    private ExerciceDTO exerciceDTO;

    public Exercice() {}

    public Exercice(ExerciceDTO exerciceDTO) {
        this.exerciceDTO = exerciceDTO;
    }

    public ExerciceDTO getExerciceByName(String name) {
        return JsonDBUtil.findObjectInJson(EXERCICE_FILE_PATH,"name", name, ExerciceDTO.class);
    }

    public ExerciceDTO addExercice(ExerciceType type, String name, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {
        ExerciceDTO exercice = ExerciceFactory.createExercice(type, name, explanation, medias, difficulty, isCustom, extraParams);
        JsonDBUtil.addObjectToJson(EXERCICE_FILE_PATH, exercice, ExerciceDTO.class);
        return exercice;
    }

    public void addCustomExercice(UUID id) {
        JsonDBUtil.addObjectToJson(CUSTOM_EXERCICE_FILE_PATH, new CustomExercice(UserSessionManager.currentUser.getId(), id), CustomExercice.class);
    }

    public List<ExerciceDTO> getAllExercices() {
        return JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, ExerciceDTO.class);
    }

    public List<ExerciceDTO> getExercicesByTypeAndDifficulty(Difficulty difficulty, ExerciceType exerciceType) {
        List<ExerciceDTO> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, ExerciceDTO.class);
        return exercices.stream()
                .filter(exercice -> exercice.getDifficulty() != null && exercice.getDifficulty().equals(difficulty) &&
                        exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public List<ExerciceDTO> getExercicesByType(ExerciceType exerciceType) {
        List<ExerciceDTO> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, ExerciceDTO.class);
        return exercices.stream()
                .filter(exercice -> exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public boolean deleteExercice(String id) {
        List<ExerciceDTO> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, ExerciceDTO.class);
        boolean removed = exercices.removeIf(exercice -> exercice.getId().equals(UUID.fromString(id)));
        if (removed) {
            JsonDBUtil.writeToJson(EXERCICE_FILE_PATH, exercices);
        }
        return removed;
    }
}
