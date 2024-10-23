package utils;

import java.util.HashMap;
import java.util.Map;

public class FeatureManager {

    private static FeatureManager instance = null;
    private Map<String, Boolean> featureStates;

    private FeatureManager() {
        featureStates = new HashMap<>();

        featureStates.put("exercice-custom-add", true);
        featureStates.put("exercice-custom-list", true);
    }

    public static FeatureManager getInstance() {
        if (instance == null) {
            instance = new FeatureManager();
        }
        return instance;
    }

    public boolean activateFeature(String featureName) {
        Boolean exist = featureStates.get(featureName);

        if(exist != null){
            featureStates.put(featureName, true);
            return true;
        }

        return false;
    }

    public boolean deactivateFeature(String featureName) {
        boolean exist = featureStates.getOrDefault(featureName, false);

        if(exist){
            featureStates.put(featureName, false);
        }

        return exist;
    }

    public boolean isFeatureActive(String featureName) {
        return featureStates.getOrDefault(featureName, false);
    }

    public Map<String, Boolean> getFeatureStates() {
        return new HashMap<>(featureStates);
    }
}
