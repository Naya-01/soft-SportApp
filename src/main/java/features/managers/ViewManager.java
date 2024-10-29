package features.managers;

import features.observers.UIViewObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ViewManager {
    private static ViewManager instance = null;
    private boolean uiViewEnabled;
    private Logger logger;
    private List<UIViewObserver> observers;

    private ViewManager() {
        logger = Logger.getLogger(this.getClass().getName());
        uiViewEnabled = true;
        observers = new ArrayList<>();
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
        notifyObservers();
    }

    public boolean isUIViewEnabled() {
        return uiViewEnabled;
    }

    public void addObserver(UIViewObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(UIViewObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (UIViewObserver observer : new ArrayList<>(observers)) {
            observer.onUIViewStateChanged(uiViewEnabled);
        }
    }
}
