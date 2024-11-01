package features.commands.community;

import static features.FeaturesEnum.COMMUNITY;
import static features.FeaturesEnum.EXERCICE_CUSTOM_LIST;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceCustomListFeature extends AbstractFeature {

    public ExerciceCustomListFeature() {
        super(EXERCICE_CUSTOM_LIST.getFeature(), true, ConstraintType.MANDATORY, COMMUNITY.getFeature());
    }
    public ExerciceCustomListFeature(boolean active) {
        super(EXERCICE_CUSTOM_LIST.getFeature(), active, ConstraintType.MANDATORY,  COMMUNITY.getFeature());
    }

}
