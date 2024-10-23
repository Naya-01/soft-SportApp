package controllers;

import models.domains.MediaDTO;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.exercices.CustomExercice;
import models.exercices.Exercice;
import models.enums.ExerciceType;
import features.FeatureManager;
import features.FeaturesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExerciceController {
    private Logger logger = Logger.getLogger("ExerciceController");
    private Exercice exerciceModel;
    private CustomExercice customExerciceModel;
    private FeatureManager featureManager;

    public ExerciceController() {
        exerciceModel = new Exercice();
        customExerciceModel = new CustomExercice();
        featureManager = FeatureManager.getInstance();
    }

    public ExerciceDTO addExercice(ExerciceType type, String name, String explanation, List<MediaDTO> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {
        if (isCustom && !featureManager.isFeatureActive(FeaturesEnum.EXERCICE_CUSTOM_ADD)) {
            logger.warning("exercice-custom-add feature is disabled.");
            return null;
        }

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

    public List<ExerciceDTO> getAllNoCustomExercices() {
        Difficulty difficulty = null;
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER)) difficulty = Difficulty.BEGINNER;
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_DIFFICULTY_INTERMEDIATE)) difficulty = Difficulty.INTERMEDIATE;
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED)) difficulty = Difficulty.ADVANCED;

        List<ExerciceType> types = new ArrayList<>();
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_TYPE_CARDIO)) types.add(ExerciceType.CARDIO);
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_TYPE_STRENGTH)) types.add(ExerciceType.STRENGTH);
        if (featureManager.isFeatureActive(FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY)) types.add(ExerciceType.FLEXIBILITY);

        return exerciceModel.getAllNoCustomExercices(difficulty, types);
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
}