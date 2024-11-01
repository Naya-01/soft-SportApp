package features.commands.exercise.media;

import static features.FeaturesEnum.EXERCICE_MEDIA;
import static features.FeaturesEnum.MEDIA_IMAGE;

import features.AbstractFeature;
import features.ConstraintType;

public class MediaImageFeature extends AbstractFeature {

    public MediaImageFeature() {
        super(MEDIA_IMAGE.getFeature(), true, ConstraintType.OR, EXERCICE_MEDIA.getFeature());
    }
    public MediaImageFeature(boolean active) {
        super(MEDIA_IMAGE.getFeature(), active, ConstraintType.OR, EXERCICE_MEDIA.getFeature());
    }
}
