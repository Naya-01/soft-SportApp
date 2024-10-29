package features.managers;

import java.util.logging.Logger;

public class ViewManager {
    private static ViewManager instance = null;
    private boolean uiViewEnabled;
    private Logger logger;

    private ViewManager() {
        logger = Logger.getLogger(this.getClass().getName());
        uiViewEnabled = true;
        logger.info("ViewManager initialisé avec uiViewEnabled = true");
    }

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public void setUIViewEnabled(boolean enabled) {
        this.uiViewEnabled = enabled;
        logger.info("UIView " + (enabled ? "activée" : "désactivée") + " dans ViewManager");
    }

    public boolean isUIViewEnabled() {
        return uiViewEnabled;
    }
}
