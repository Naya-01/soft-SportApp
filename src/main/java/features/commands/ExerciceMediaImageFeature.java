package features.commands;

import static features.FeaturesEnum.EXERCICE_MEDIA_IMAGE;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceMediaImageFeature extends AbstractFeature {

    public ExerciceMediaImageFeature() {
        super(EXERCICE_MEDIA_IMAGE.getFeature(), true, true, ConstraintType.OR, "medias", "media");
    }
    public ExerciceMediaImageFeature(boolean active) {
        super(EXERCICE_MEDIA_IMAGE.getFeature(), active, true, ConstraintType.OR, "medias", "media");
    }
}
