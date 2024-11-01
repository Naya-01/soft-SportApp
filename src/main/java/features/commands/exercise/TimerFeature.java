package features.commands.exercise;

import static features.FeaturesEnum.EXERCICE_TIMER;
import static features.FeaturesEnum.EXERCISE;

import features.AbstractFeature;
import features.ConstraintType;

public class TimerFeature extends AbstractFeature {

    public TimerFeature() {
        super(EXERCICE_TIMER.getFeature(), true, ConstraintType.OPTIONAL, EXERCISE.getFeature());
    }
    public TimerFeature(boolean active) {
        super(EXERCICE_TIMER.getFeature(), active, ConstraintType.OPTIONAL, EXERCISE.getFeature());
    }
}
