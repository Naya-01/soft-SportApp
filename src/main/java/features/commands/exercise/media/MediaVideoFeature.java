package features.commands.exercise.media;

import static features.FeaturesEnum.EXERCICE_MEDIA;
import static features.FeaturesEnum.MEDIA_VIDEO;

import features.AbstractFeature;
import features.ConstraintType;

public class MediaVideoFeature extends AbstractFeature {

    public MediaVideoFeature() {
        super(MEDIA_VIDEO.getFeature(), true, ConstraintType.OR, EXERCICE_MEDIA.getFeature());
    }
    public MediaVideoFeature(boolean active) {
        super(MEDIA_VIDEO.getFeature(), active, ConstraintType.OR, EXERCICE_MEDIA.getFeature());
    }
}
