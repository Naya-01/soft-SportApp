package features.commands;

import static features.FeaturesEnum.MEDIA_VIDEO;

import features.AbstractFeature;
import features.ConstraintType;

public class MediaVideoFeature extends AbstractFeature {

    public MediaVideoFeature() {
        super(MEDIA_VIDEO.getFeature(), true, true, ConstraintType.OR, "medias", "media");
    }
    public MediaVideoFeature(boolean active) {
        super(MEDIA_VIDEO.getFeature(), active, true, ConstraintType.OR, "medias", "media");
    }
}
