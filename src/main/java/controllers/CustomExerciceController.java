package controllers;

import models.User;
import models.domains.CustomExerciceDTO;
import models.domains.ExerciceDTO;
import models.domains.UserDTO;
import models.exercices.CustomExercice;
import models.domains.CustomExerciceDetailsDTO;
import models.exercices.CustomExerciceDetails;
import models.exercices.Exercice;

import java.util.List;
import java.util.logging.Logger;

public class CustomExerciceController implements ControllerInterface {

    private Logger logger = Logger.getLogger("ExerciceController");

    private User userModel;
    private Exercice exerciceModel;
    private CustomExercice customExerciceModel;
    private CustomExerciceDetails customExerciceDetailsModel;

    public CustomExerciceController() {
        userModel = new User();
        exerciceModel = new Exercice();
        customExerciceModel = new CustomExercice();
        customExerciceDetailsModel = new CustomExerciceDetails();
    }

    public List<CustomExerciceDetailsDTO> getCustomExercicesWithDetails() {
        List<CustomExerciceDTO> customExercices = customExerciceModel.getAllCustomExercices();
        List<UserDTO> users = userModel.getAllUsers();
        List<ExerciceDTO> exercices = exerciceModel.getAllExercices();

        return customExerciceDetailsModel.getCustomExercicesWithDetails(customExercices, users, exercices);
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
