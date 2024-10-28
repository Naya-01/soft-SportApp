package features.commands;

import static features.FeaturesEnum.EXERCICE_MEDIA_VIDEO;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceMediaVideoFeature extends AbstractFeature {

    public ExerciceMediaVideoFeature() {
        super(EXERCICE_MEDIA_VIDEO.getFeature(), true, true, ConstraintType.OR, "medias", "media");
    }
    public ExerciceMediaVideoFeature(boolean active) {
        super(EXERCICE_MEDIA_VIDEO.getFeature(), active, true, ConstraintType.OR, "medias", "media");
    }
}
