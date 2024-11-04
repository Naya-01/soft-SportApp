package features.commands.exercise.difficulty;

import static features.FeaturesEnum.DIFFICULTY;
import static features.FeaturesEnum.EXERCISE;

import features.AbstractFeature;
import features.ConstraintType;

public class Difficulty extends AbstractFeature {

    public Difficulty() {
        super(DIFFICULTY.getFeature(), true, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }
    public Difficulty(boolean active) {
        super(DIFFICULTY.getFeature(), active, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }

}
