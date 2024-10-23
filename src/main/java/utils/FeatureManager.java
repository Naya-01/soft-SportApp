package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

public class FeatureManager {

    private static FeatureManager instance = null;
    private Map<String, Boolean> featureStates;

    private FeatureManager() {
        featureStates = new HashMap<>();
        loadFeatureStates();
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
}
