package controllers;

import features.managers.FeatureManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractController implements ControllerInterface {
    protected Logger logger;
    protected FeatureManager featureManager;

    public AbstractController() {
        logger = Logger.getLogger(this.getClass().getName());
        featureManager = FeatureManager.getInstance();
    }

    @Override
    public int activate(String[] deactivations, String[] activations){
        boolean ok;
        int failed = 0;

        if (deactivations != null && !deactivations[0].isEmpty()) {
            for (String feature : deactivations) {
                ok = featureManager.deactivate(feature);
                if(ok) {
                    logger.info("Feature deactivated: " + feature);
                } else {
                    failed = -1;
                }
            }
        }

        if (activations != null && !activations[0].isEmpty()) {
            for (String feature : activations) {
                ok = featureManager.activate(feature);
                if(ok) {
                    logger.info("Feature activated: " + feature);
                } else {
                    failed = -1;
                }
            }
        }

        return failed;
    }

    @Override
    public abstract boolean enableUIView();

    @Override
    public abstract boolean disableUIView();


    public String[] getStateAsLog(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : getStateAsLog");

        return new String[0];
    }
}
