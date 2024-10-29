package features.commands;

import static features.FeaturesEnum.MEDIA_IMAGE;

import features.AbstractFeature;
import features.ConstraintType;

public class MediaImageFeature extends AbstractFeature {

    public MediaImageFeature() {
        super(MEDIA_IMAGE.getFeature(), true, true, ConstraintType.OR, "medias", "media");
    }
    public MediaImageFeature(boolean active) {
        super(MEDIA_IMAGE.getFeature(), active, true, ConstraintType.OR, "medias", "media");
    }
}
