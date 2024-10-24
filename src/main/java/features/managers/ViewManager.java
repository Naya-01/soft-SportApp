package features.managers;

import java.util.HashMap;
import java.util.Map;

public class ViewManager extends StateManager {

    private static ViewManager instance = null;

    private ViewManager() {
        super("ViewManager");
        loadStates("view-states.properties");

    }

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public Map<String, Boolean> getViewStates() {
        return new HashMap<>( super.states);
    }

    @Override
    public boolean activate(String featureName) {
        if (isParentFeatureActive(featureName)) {
            super.states.put(featureName, true);
            logger.info("View activated: " + featureName);
            return true;
        }
        return false;
    }

    @Override
    public boolean deactivate(String featureName) {
        if (super.states.containsKey(featureName)) {
            super.states.put(featureName, false);
            logger.info("View deactivated: " + featureName);
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive(String featureName) {
        if (!isParentFeatureActive(featureName)) {
            return false;
        }
        return super.states.getOrDefault(featureName, false);
    }

    private boolean isParentFeatureActive(String featureName) {
        String[] parts = featureName.split("-");
        String parentFeature = parts[0];

        if(featureName.equals(parentFeature)){
            return true;
        }

        return super.states.getOrDefault(parentFeature, true);
    }
}
