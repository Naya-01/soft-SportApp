package features.commands.exercise.media;

import static features.FeaturesEnum.EXERCICE_MEDIA;
import static features.FeaturesEnum.EXERCISE;

import features.AbstractFeature;
import features.ConstraintType;

public class Media extends AbstractFeature {

    public Media() {
        super(EXERCICE_MEDIA.getFeature(), true, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }
    public Media(boolean active) {
        super(EXERCICE_MEDIA.getFeature(), active, ConstraintType.MANDATORY, EXERCISE.getFeature());
    }

}
