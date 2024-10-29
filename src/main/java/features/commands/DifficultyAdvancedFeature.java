package features.commands;

import static features.FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED;

import features.AbstractFeature;
import features.ConstraintType;
public class DifficultyAdvancedFeature extends AbstractFeature {

    public DifficultyAdvancedFeature() {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), false, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
    public DifficultyAdvancedFeature(boolean active) {
        super(EXERCICE_DIFFICULTY_ADVANCED.getFeature(), active, true, ConstraintType.ALTERNATIVE, "difficulty", "exercise");
    }
}
