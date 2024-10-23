package features;

import java.util.Map;

public class OrFeatureStrategy implements FeatureStrategy {
    @Override
    public void activateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith) {
        featureStates.put(featureName, true);
    }

    @Override
    public void deactivateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith) {
        int nbActive = 0;
        for (String feature : featureStates.keySet()) {
            if (feature.startsWith(startsWith) && featureStates.get(feature)) {
                nbActive++;
            }
        }

        if (nbActive > 1) {
            featureStates.put(featureName, false);
        }
    }
}
