package features.commands;

import static features.FeaturesEnum.MEDIA_VIDEO;

import features.AbstractFeature;
import features.ConstraintType;

public class VideoFeature extends AbstractFeature {

    public VideoFeature() {
        super(MEDIA_VIDEO.getFeature(), true, true, ConstraintType.OR, "medias", "media");
    }
    public VideoFeature(boolean active) {
        super(MEDIA_VIDEO.getFeature(), active, true, ConstraintType.OR, "medias", "media");
    }
}
