package features.commands;

import static features.FeaturesEnum.EXERCICE_TIMER;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceTimerFeature extends AbstractFeature {

    public ExerciceTimerFeature() {
        super(EXERCICE_TIMER.getFeature(), true, false, ConstraintType.OPTIONAL, "timer", "exercise");
    }
    public ExerciceTimerFeature(boolean active) {
        super(EXERCICE_TIMER.getFeature(), active, false, ConstraintType.OPTIONAL, "timer", "exercise");
    }
}
