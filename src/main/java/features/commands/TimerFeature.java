package features.commands;

import static features.FeaturesEnum.EXERCICE_TIMER;

import features.AbstractFeature;
import features.ConstraintType;

public class TimerFeature extends AbstractFeature {

    public TimerFeature() {
        super(EXERCICE_TIMER.getFeature(), true, false, ConstraintType.OPTIONAL, "timer", "exercise");
    }
    public TimerFeature(boolean active) {
        super(EXERCICE_TIMER.getFeature(), active, false, ConstraintType.OPTIONAL, "timer", "exercise");
    }
}
