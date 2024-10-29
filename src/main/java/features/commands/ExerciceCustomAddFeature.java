package features.commands;

import static features.FeaturesEnum.EXERCICE_CUSTOM_ADD;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceCustomAddFeature extends AbstractFeature {

    public ExerciceCustomAddFeature() {
        super(EXERCICE_CUSTOM_ADD.getFeature(), true, false, ConstraintType.OPTIONAL, "community", "community");
    }
    public ExerciceCustomAddFeature(boolean active) {
        super(EXERCICE_CUSTOM_ADD.getFeature(), active, false, ConstraintType.OPTIONAL, "community", "community");
    }
}
