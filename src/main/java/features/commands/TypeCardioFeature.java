package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_CARDIO;

import features.AbstractFeature;
import features.ConstraintType;

public class TypeCardioFeature extends AbstractFeature {

    public TypeCardioFeature() {
        super(EXERCICE_TYPE_CARDIO.getFeature(), true, true,  ConstraintType.OR, "exercise_type", "type");
    }

    public TypeCardioFeature(boolean active) {
        super(EXERCICE_TYPE_CARDIO.getFeature(), active, true,  ConstraintType.OR, "exercise_type", "type");
    }
}