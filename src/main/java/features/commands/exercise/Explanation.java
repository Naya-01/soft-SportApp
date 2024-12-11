package features.commands.exercise;

import features.AbstractFeature;
import features.ConstraintType;

import static features.FeaturesEnum.*;

public class Explanation extends AbstractFeature {

    public Explanation() {
        super(EXPLANATION.getFeature(), true, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }
    public Explanation(boolean active) {
        super(EXPLANATION.getFeature(), active, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }
}
