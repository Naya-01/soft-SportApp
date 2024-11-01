package features.commands.exercise.difficulty;

import static features.FeaturesEnum.DIFFICULTY;
import static features.FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED;

import features.AbstractFeature;
import features.ConstraintType;
public class DifficultyAdvancedFeature extends AbstractFeature {

    public DifficultyAdvancedFeature() {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), false,  ConstraintType.ALTERNATIVE, DIFFICULTY.getFeature());
    }
    public DifficultyAdvancedFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), active, ConstraintType.ALTERNATIVE,  DIFFICULTY.getFeature());
    }
}
