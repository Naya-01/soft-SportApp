package features.commands.exercise.difficulty;

import static features.FeaturesEnum.DIFFICULTY;
import static features.FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER;

import features.AbstractFeature;
import features.ConstraintType;

public class DifficultyBeginnerFeature extends AbstractFeature {

    public DifficultyBeginnerFeature() {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), true, ConstraintType.ALTERNATIVE,  DIFFICULTY.getFeature());
    }
    public DifficultyBeginnerFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), active, ConstraintType.ALTERNATIVE,  DIFFICULTY.getFeature());
    }
}
