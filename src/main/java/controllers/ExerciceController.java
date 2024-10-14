package controllers;

import controllers.factories.ExerciceFactory;
import models.Media;
import models.enums.Difficulty;
import models.exercices.CustomExercice;
import models.exercices.Exercice;
import models.enums.ExerciceType;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;

public class ExerciceController implements ControllerInterface {

    private static final String EXERCICE_FILE_PATH = "src/main/java/data/exercices.json";
    private static final String CUSTOM_EXERCICE_FILE_PATH = "src/main/java/data/custom-exercices.json";

    public Exercice addExercice(ExerciceType type, String name, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {

        Exercice exercice = ExerciceFactory.createExercice(type, name, explanation, medias, difficulty, isCustom, extraParams);
        if (isCustom) {
            JsonDBUtil.addObjectToJson(CUSTOM_EXERCICE_FILE_PATH, new CustomExercice(UserSessionManager.currentUser.getId(), exercice.getId()), CustomExercice.class);
        }

        JsonDBUtil.addObjectToJson(EXERCICE_FILE_PATH, exercice, Exercice.class);

        return exercice;
    }

    public List<Exercice> getAllExercices() {
        return JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
    }

    public List<Exercice> getExercicesByTypeAndDifficulty(Difficulty difficulty, ExerciceType exerciceType) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        return exercices.stream()
                .filter(exercice -> exercice.getDifficulty() != null && exercice.getDifficulty().equals(difficulty) &&
                        exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public List<Exercice> getExercicesByType(ExerciceType exerciceType) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        return exercices.stream()
                .filter(exercice -> exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public boolean deleteExercice(String id) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        boolean removed = exercices.removeIf(exercice -> exercice.getId().equals(UUID.fromString(id)));
        if (removed) {
            JsonDBUtil.writeToJson(EXERCICE_FILE_PATH, exercices);
        }
        return removed;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        return 0;
    }

    @Override
    public boolean enableUIView() {
        return false;
    }

    @Override
    public boolean disableUIView() {
        return false;
    }

    @Override
    public String[] getStateAsLog() {
        return new String[0];
    }
}