package features.managers;

import features.AlternativeFeatureStrategy;
import features.FeatureStrategy;
import features.FeaturesEnum;
import features.OrFeatureStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FeatureManager extends StateManager{

    private static FeatureManager instance = null;
    private Map<String, FeatureStrategy> featureStrategies;

    public FeatureManager() {
        super();

        featureStrategies = new HashMap<>();
        loadFeatureStrategies();
    }

    public static FeatureManager getInstance() {
        if (instance == null) {
            instance = new FeatureManager();
        }
        return instance;
    }

    public boolean activate(String featureName) {
        String[] splitted = featureName.split("_");
        String startsWith = splitted[0] + "_" + splitted[1] + "_";

        if (featureStrategies.containsKey(startsWith)) {
            featureStrategies.get(startsWith).activateFeature(super.states, featureName, startsWith);
            return true;
        } else if (super.states.get(featureName) != null) {
            super.states.put(featureName, true);
            logger.info("Feature set to true: " + featureName);
            return true;
        }

        return false;
    }

    public boolean deactivate(String featureName) {
        String[] splitted = featureName.split("_");
        String startsWith = splitted[0] + "_" + splitted[1] + "_";

        if (featureStrategies.containsKey(startsWith)) {
            featureStrategies.get(startsWith).deactivateFeature(super.states, featureName, startsWith);
            return true;
        } else if (super.states.get(featureName) != null) {
            super.states.put(featureName, false);
            logger.info("Feature set to false: " + featureName);
            return true;
        }

        return false;
    }

    public boolean isFeatureActive(FeaturesEnum featureEnum) {
        return super.states.getOrDefault(featureEnum.getFeature(), false);
    }

    public boolean isActive(String featureName) {
        return super.states.getOrDefault(featureName.toLowerCase(), false);
    }

    public Map<String, Boolean> getFeatureStates() {
        return new HashMap<>(super.states);
    }

    private void loadFeatureStrategies() {
        featureStrategies.put("exercice_difficulty_", new AlternativeFeatureStrategy());
        featureStrategies.put("exercice_type_", new OrFeatureStrategy());
        featureStrategies.put("exercice_media_", new OrFeatureStrategy());
        featureStrategies.put("payment_method_", new OrFeatureStrategy());
    }
}
