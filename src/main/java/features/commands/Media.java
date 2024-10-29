package features.commands;

import static features.FeaturesEnum.EXERCICE_MEDIA;

import features.AbstractFeature;
import features.ConstraintType;

public class Media extends AbstractFeature {

    public Media() {
        super(EXERCICE_MEDIA.getFeature(), true, true, ConstraintType.MANDATORY, "medias", "exercise");
    }
    public Media(boolean active) {
        super(EXERCICE_MEDIA.getFeature(), active, true, ConstraintType.MANDATORY, "medias", "exercise");
    }

}
