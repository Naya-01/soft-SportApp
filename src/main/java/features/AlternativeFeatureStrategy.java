package features;

import java.util.Map;

public class AlternativeFeatureStrategy implements FeatureStrategy {
    @Override
    public void activateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith) {
        // Mettre la feature demandée à vrai et désactiver toutes les autres pour respecter l'alternative
        for (String feature : featureStates.keySet()) {
            if (feature.startsWith(startsWith)) {
                featureStates.put(feature, false);
            }
        }

        featureStates.put(featureName, true);
    }

    @Override
    public void deactivateFeature(Map<String, Boolean> featureStates, String featureName, String startsWith) {
        // Désactiver la feature mais la première trouvée à true pour respecter l'alternative
        featureStates.put(featureName, false);

        for (String feature : featureStates.keySet()) {
            if (feature.startsWith(startsWith) && !feature.equals(featureName)) {
                featureStates.put(feature, true);
                return;
            }
        }
    }
}
