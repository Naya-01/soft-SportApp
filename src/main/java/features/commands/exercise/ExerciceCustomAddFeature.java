package features.commands.exercise;

import static features.FeaturesEnum.COMMUNITY;
import static features.FeaturesEnum.EXERCICE_CUSTOM_ADD;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceCustomAddFeature extends AbstractFeature {

    public ExerciceCustomAddFeature() {
        super(EXERCICE_CUSTOM_ADD.getFeature(), true, ConstraintType.OPTIONAL, COMMUNITY.getFeature());
    }
    public ExerciceCustomAddFeature(boolean active) {
        super(EXERCICE_CUSTOM_ADD.getFeature(), active, ConstraintType.OPTIONAL, COMMUNITY.getFeature());
    }
}
