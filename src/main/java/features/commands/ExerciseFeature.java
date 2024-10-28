package features.commands;

import static features.FeaturesEnum.EXERCISE;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciseFeature extends AbstractFeature {


    public ExerciseFeature() {
        super(EXERCISE.getFeature(), true, true, ConstraintType.MANDATORY, "exercise", null);
    }
    public ExerciseFeature(boolean active) {
        super(EXERCISE.getFeature(), active, true, ConstraintType.MANDATORY, "exercise", null);
    }

}
