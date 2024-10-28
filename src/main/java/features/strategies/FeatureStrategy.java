package features.strategies;

import features.Feature;
import java.util.List;
import java.util.Map;

public interface FeatureStrategy {
    void activateFeature(Feature feature, Map<String, Feature> features);
    void deactivateFeature(Feature feature, Map<String, Feature> features);
}
