package controllers;

import features.managers.FeatureManager;
import features.managers.ViewManager;
import models.User;
import models.domains.CustomExerciceDTO;
import models.domains.ExerciceDTO;
import models.domains.UserDTO;
import models.exercices.CustomExercice;
import models.domains.CustomExerciceDetailsDTO;
import models.exercices.CustomExerciceDetails;
import models.exercices.Exercice;
import features.FeaturesEnum;

import java.util.List;
import java.util.logging.Logger;
import utils.ViewEnum;

public class CommunityController{

    private Logger logger = Logger.getLogger("CommunityController");

    private User userModel;
    private Exercice exerciceModel;
    private CustomExercice customExerciceModel;
    private CustomExerciceDetails customExerciceDetailsModel;
    private FeatureManager featureManager;
    private ViewManager viewManager;

    public CommunityController() {
        userModel = new User();
        exerciceModel = new Exercice();
        customExerciceModel = new CustomExercice();
        customExerciceDetailsModel = new CustomExerciceDetails();
        featureManager = FeatureManager.getInstance();
        viewManager = ViewManager.getInstance();
    }

    public List<CustomExerciceDetailsDTO> getCustomExercicesWithDetails() {

        if (!featureManager.isActive(FeaturesEnum.EXERCICE_CUSTOM_LIST.getFeature())) {
            logger.warning("exercice-custom-list feature is disabled.");
            return null;
        }

        List<CustomExerciceDTO> customExercices = customExerciceModel.getAllCustomExercices();
        List<UserDTO> users = userModel.getAllUsers();
        List<ExerciceDTO> exercices = exerciceModel.getAllExercices();

        return customExerciceDetailsModel.getCustomExercicesWithDetails(customExercices, users, exercices);
    }
}
