package controllers;

import utils.FeatureManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerImpl implements ControllerInterface{

    private Logger logger = Logger.getLogger("ControllerImpl");
    private FeatureManager featureManager;

    public ControllerImpl() {
        featureManager = FeatureManager.getInstance();
    }

    public int activate(String[] deactivations, String[] activations){
        boolean ok;
        int failed = 0;

        if (deactivations != null) {
            for (String feature : deactivations) {
                ok = featureManager.deactivateFeature(feature);
                if(ok) {
                    logger.info("Feature deactivated: " + feature);
                } else {
                    failed = -1;
                }
            }
        }

        if (activations != null) {
            for (String feature : activations) {
                ok = featureManager.activateFeature(feature);
                if(ok) {
                    logger.info("Feature activated: " + feature);
                } else {
                    failed = -1;
                }
            }
        }

        return failed;
    }

    public boolean enableUIView(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : enableUIView");

        return true;
    }

    public boolean disableUIView(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : disableUIView");

        return true;
    }

    public String[] getStateAsLog(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : getStateAsLog");

        return new String[0];
    }
}
