package features;

import java.util.List;

public class FeatureGroup {
    private String name;
    private ConstraintType constraintType;
    private List<Feature> features;

    public FeatureGroup(String name, ConstraintType constraintType, List<Feature> features) {
        this.name = name;
        this.constraintType = constraintType;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public ConstraintType getConstraintType() {
        return constraintType;
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
