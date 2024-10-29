package features.commands;

import static features.FeaturesEnum.EXERCICE_CUSTOM_LIST;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceCustomListFeature extends AbstractFeature {

    public ExerciceCustomListFeature() {
        super(EXERCICE_CUSTOM_LIST.getFeature(), true, false, ConstraintType.OPTIONAL, "community", "community");
    }
    public ExerciceCustomListFeature(boolean active) {
        super(EXERCICE_CUSTOM_LIST.getFeature(), active, false, ConstraintType.OPTIONAL, "community", "community");
    }

}
