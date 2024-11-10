package views;

import controllers.ExerciceController;

import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;

public class CustomExerciceCreationView extends BaseView {

    private ExerciceController exerciceController;
    private Logger logger;

    public CustomExerciceCreationView() {
        this.exerciceController = new ExerciceController();
        logger = Log.getLogger();
        logger.info("Custom Exercice Creation view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("Custom Exercice Creation");
    }

    @Override
    public void onFeatureStateChanged(String featureName, boolean isActive) {

    }
}
