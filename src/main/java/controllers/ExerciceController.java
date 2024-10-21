package controllers;

import models.domains.MediaDTO;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.exercices.CustomExercice;
import models.exercices.Exercice;
import models.enums.ExerciceType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExerciceController implements ControllerInterface {
    private Logger logger = Logger.getLogger("ExerciceController");
    private Exercice exerciceModel;
    private CustomExercice customExerciceModel;

    public ExerciceController() {
        exerciceModel = new Exercice();
        customExerciceModel = new CustomExercice();
    }

    public ExerciceDTO addExercice(ExerciceType type, String name, String explanation, List<MediaDTO> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {
        if (name == null || name.isEmpty()) {
            logger.log(Level.WARNING, "Add Exercice failed: Fields missing or empty");
            return null;
        }

        if (exerciceModel.getExerciceByName(name) != null) {
            logger.log(Level.WARNING, "Add Exercice failed: Exercice already exists - " + name);
            return null;
        }

        ExerciceDTO ex = exerciceModel.addExercice(type, name, explanation, medias, difficulty, isCustom);
        if (isCustom) {
            customExerciceModel.addCustomExercice(ex.getId());
        }

        return ex;
    }

    public List<ExerciceDTO> getAllExercices() {
        return exerciceModel.getAllExercices();
    }

    public List<ExerciceDTO> getExercicesByTypeAndDifficulty(Difficulty difficulty, ExerciceType exerciceType) {
        return exerciceModel.getExercicesByTypeAndDifficulty(difficulty, exerciceType);
    }

    public List<ExerciceDTO> getExercicesByType(ExerciceType exerciceType) {
        return exerciceModel.getExercicesByType(exerciceType);
    }

    public boolean deleteExercice(String id) {
        return exerciceModel.deleteExercice(id);
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