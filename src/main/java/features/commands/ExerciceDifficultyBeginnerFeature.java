package features.commands;

import static features.FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceDifficultyBeginnerFeature extends AbstractFeature {

    public ExerciceDifficultyBeginnerFeature() {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), true, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
    public ExerciceDifficultyBeginnerFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), active, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
}
