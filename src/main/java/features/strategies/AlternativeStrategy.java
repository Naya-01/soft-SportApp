package features.strategies;

import features.Feature;
import java.util.Map;
import java.util.logging.Logger;

public class AlternativeStrategy implements FeatureStrategy {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void activateFeature(Feature feature, Map<String, Feature> features) {
        String groupName = feature.getGroupName();
        for (Feature f : features.values()) {
            if (f.getGroupName() != null && f.getGroupName().equals(groupName)) {
                if (f.isActive()) {
                    f.setActive(false);
                    logger.info("Feature désactivée en raison de la contrainte ALTERNATIVE : " + f.getName());
                }
            }
        }
        feature.setActive(true);
        logger.info("Feature activée : " + feature.getName());
    }

    @Override
    public void deactivateFeature(Feature feature, Map<String, Feature> features) {
        String groupName = feature.getGroupName();
        long activeCount = features.values().stream()
            .filter(f -> f.getGroupName() != null && f.getGroupName().equals(groupName))
            .filter(Feature::isActive)
            .count();

        if (activeCount <= 1) {
            logger.warning("Impossible de désactiver la seule feature active dans un groupe ALTERNATIVE : " + feature.getName());
            return;
        }

        feature.setActive(false);
        logger.info("Feature désactivée : " + feature.getName());
    }
}
