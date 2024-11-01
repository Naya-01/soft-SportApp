package features.commands.exercise;

import static features.FeaturesEnum.EXERCISE;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciseFeature extends AbstractFeature {


    public ExerciseFeature() {
        super(EXERCISE.getFeature(), true, ConstraintType.MANDATORY, null);
    }
    public ExerciseFeature(boolean active) {
        super(EXERCISE.getFeature(), active, ConstraintType.MANDATORY, null);
    }

}
