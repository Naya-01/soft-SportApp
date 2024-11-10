package models.exercices;

import models.domains.CustomExerciceDTO;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;

public class CustomExercice {

    private static final String CUSTOM_EXERCICE_FILE_PATH = "src/main/java/data/custom-exercices.json";

    public CustomExercice() {}

    public void addCustomExercice(UUID id) {
        JsonDBUtil.addObjectToJson(CUSTOM_EXERCICE_FILE_PATH, new CustomExerciceDTO(UserSessionManager.currentUser.getId(), id), CustomExerciceDTO.class);
    }

    public List<CustomExerciceDTO> getAllCustomExercices() {
        return JsonDBUtil.readFromJson(CUSTOM_EXERCICE_FILE_PATH, CustomExerciceDTO.class);
    }
}
