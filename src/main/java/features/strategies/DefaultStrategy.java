package features.strategies;

import features.Feature;
import java.util.Map;
import java.util.logging.Logger;

public class DefaultStrategy implements FeatureStrategy {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void activateFeature(Feature feature, Map<String, Feature> features) {
        feature.setActive(true);
        logger.info("Feature activée : " + feature.getName());
    }

    @Override
    public void deactivateFeature(Feature feature, Map<String, Feature> features) {
        if (feature.isMandatory()) {
            logger.warning("Impossible de désactiver une feature MANDATORY : " + feature.getName());
            return;
        }
        feature.setActive(false);
        logger.info("Feature désactivée : " + feature.getName());
    }
}
