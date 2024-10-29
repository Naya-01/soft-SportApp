package features.commands;

import static features.FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER;

import features.AbstractFeature;
import features.ConstraintType;

public class DifficultyBeginnerFeature extends AbstractFeature {

    public DifficultyBeginnerFeature() {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), true, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
    public DifficultyBeginnerFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_BEGINNER.getFeature(), active, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
}
