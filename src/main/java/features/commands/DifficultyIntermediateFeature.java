package features.commands;

import static features.FeaturesEnum.EXERCICE_DIFFICULTY_INTERMEDIATE;

import features.AbstractFeature;
import features.ConstraintType;

public class DifficultyIntermediateFeature extends AbstractFeature {

    public DifficultyIntermediateFeature() {
        super(EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature(), false, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
    public DifficultyIntermediateFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature(), active, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
}
