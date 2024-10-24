package features;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class FeatureManager {

    private Logger logger = Logger.getLogger("FeatureManager");
    private static FeatureManager instance = null;
    private Map<String, Boolean> featureStates;
    private Map<String, FeatureStrategy> featureStrategies;

    private FeatureManager() {
        featureStates = new HashMap<>();
        loadFeatureStates();

        featureStrategies = new HashMap<>();
        loadFeatureStrategies();
    }

    public static FeatureManager getInstance() {
        if (instance == null) {
            instance = new FeatureManager();
        }
        return instance;
    }

    public boolean activateFeature(String featureName) {
        String[] splitted = featureName.split("-");
        String startsWith = splitted[0] + "-" + splitted[1] + "-";

        if (featureStrategies.containsKey(startsWith)) {
            featureStrategies.get(startsWith).activateFeature(featureStates, featureName, startsWith);
            return true;
        } else if (featureStates.get(featureName) != null) {
            featureStates.put(featureName, true);
            logger.info("Feature set to true: " + featureName);
            return true;
        }

        return false;
    }

    public boolean deactivateFeature(String featureName) {
        String[] splitted = featureName.split("-");
        String startsWith = splitted[0] + "-" + splitted[1] + "-";

        if (featureStrategies.containsKey(startsWith)) {
            featureStrategies.get(startsWith).deactivateFeature(featureStates, featureName, startsWith);
            return true;
        } else if (featureStates.get(featureName) != null) {
            featureStates.put(featureName, false);
            logger.info("Feature set to false: " + featureName);
            return true;
        }

        return false;
    }

    public boolean isFeatureActive(FeaturesEnum featureEnum) {
        return featureStates.getOrDefault(featureEnum.getFeature(), false);
    }

    public boolean isFeatureActive(String featureName) {
        return featureStates.getOrDefault(featureName.toLowerCase(), false);
    }

    public Map<String, Boolean> getFeatureStates() {
        return new HashMap<>(featureStates);
    }

    private void loadFeatureStates() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("feature-states.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find feature-states.properties");
                return;
            }

            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                featureStates.put(key, Boolean.parseBoolean(properties.getProperty(key)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadFeatureStrategies() {
        featureStrategies.put("exercice-difficulty-", new AlternativeFeatureStrategy());
        featureStrategies.put("exercice-type-", new OrFeatureStrategy());
        featureStrategies.put("exercice-media-", new OrFeatureStrategy());
        featureStrategies.put("payment-method-", new OrFeatureStrategy());
    }
}
