package features.commands;

import static features.FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED;

import features.AbstractFeature;
import features.ConstraintType;
public class ExerciceDifficultyAdvancedFeature extends AbstractFeature {

    public ExerciceDifficultyAdvancedFeature() {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), false, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
    public ExerciceDifficultyAdvancedFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), active, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
}
