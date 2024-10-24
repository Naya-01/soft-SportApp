package controllers;

import features.managers.FeatureManager;
import features.FeaturesEnum;
import models.Performance;
import models.domains.ExerciceDTO;
import models.exercices.Exercice;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PerformanceController {

    private Logger logger = Logger.getLogger("PerformanceController");
    private FeatureManager featureManager;
    private Exercice exerciceModel;
    private Performance performanceModel;

    public PerformanceController() {
        featureManager = FeatureManager.getInstance();
        exerciceModel = new Exercice();
        performanceModel = new Performance();
    }

    public boolean addExercicePerformance(String exerciceId, String performanceText) {
        if (!featureManager.isFeatureActive(FeaturesEnum.EXERCICE_PERFORMANCE)) {
            logger.warning("exercice-performance feature is disabled.");
            return false;
        }

        if (performanceText == null || performanceText.isEmpty()) {
            logger.warning("Add exercice performance failed: Fields missing or empty");
            return false;
        }

        if (exerciceModel.getExerciceById(exerciceId) == null) {
            logger.warning("Add exercice performance failed: Exercice does not exist");
            return false;
        }

        performanceModel.addExercicePerformance(exerciceId, performanceText);
        logger.log(Level.INFO, "Performance successfully added");
        return true;
    }

    public String getExercicePerformanceTextOfUser(String exerciceId) {
        if (!featureManager.isFeatureActive(FeaturesEnum.EXERCICE_PERFORMANCE)) {
            logger.warning("exercice-performance feature is disabled.");
            return null;
        }

        ExerciceDTO ex = exerciceModel.getExerciceById(exerciceId);
        if (ex == null) {
            logger.warning("Get exercice performance failed: Exercice does not exist");
            return null;
        }

        return performanceModel.getExercicePerformanceTextOfUser(exerciceId);
    }

}
