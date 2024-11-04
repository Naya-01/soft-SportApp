package controllers;

import features.Feature;
import features.managers.FeatureManager;
import features.managers.ViewManager;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Log;

public class MainController implements ControllerInterface {
    private Logger logger;
    private FeatureManager featureManager;
    private ViewManager viewManager;

    public MainController() {
        Log.config();
        logger = Log.getLogger();
        logger.log(Level.INFO, "Server started");


        featureManager = FeatureManager.getInstance();
        viewManager = ViewManager.getInstance();
        logger.info("MainController initialisé");
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        boolean ok;
        int failed = 0;

        if (activations != null && activations.length > 0 && !activations[0].isEmpty()) {
            for (String feature : activations) {
                ok = featureManager.activate(feature);
                if (ok) {
                    logger.info("Feature activée: " + feature);
                } else {
                    logger.warning("Échec de l'activation de la feature: " + feature);
                    failed = -1;
                }
            }
        }

        if (deactivations != null && deactivations.length > 0 && !deactivations[0].isEmpty()) {
            for (String feature : deactivations) {
                ok = featureManager.deactivate(feature);
                if (ok) {
                    //logger.info("Feature désactivée: " + feature);
                } else {
                    logger.warning("Échec de la désactivation de la feature: " + feature);
                    failed = -1;
                }
            }
        }

        // Pour le moment, on retourne simplement le statut
        return failed;
    }

    @Override
    public boolean enableUIView() {
        viewManager.setUIViewEnabled(true);
        return true;
    }

    @Override
    public boolean disableUIView() {
        viewManager.setUIViewEnabled(false);
        return true;
    }


    @Override
    public String[] getStateAsLog() {
        logger.info("Obtention de l'état actuel des features");
        Map<String, Feature> featureStates = featureManager.getFeatures();
        String[] logs = new String[featureStates.size()];
        int i = 0;
        for (Feature feature : featureStates.values()) {
            logs[i] = feature.getName() + "=" + feature.isActive();
            i++;
        }
        return logs;
    }
}
