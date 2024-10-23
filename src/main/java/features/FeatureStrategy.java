package features;

import java.util.Map;

public interface FeatureStrategy {
    void activateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith);
    void deactivateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith);
}
