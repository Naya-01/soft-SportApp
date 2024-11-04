package features.commands.exercise.difficulty;

import static features.FeaturesEnum.DIFFICULTY;
import static features.FeaturesEnum.EXERCICE_DIFFICULTY_INTERMEDIATE;

import features.AbstractFeature;
import features.ConstraintType;

public class DifficultyIntermediateFeature extends AbstractFeature {

    public DifficultyIntermediateFeature() {
        super(EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature(), false, ConstraintType.ALTERNATIVE,  DIFFICULTY.getFeature());
    }
    public DifficultyIntermediateFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature(), active, ConstraintType.ALTERNATIVE,  DIFFICULTY.getFeature());
    }
}
