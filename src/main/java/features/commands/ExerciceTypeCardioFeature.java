package features.commands;

import static features.FeaturesEnum.EXERCICE_TYPE_CARDIO;

import features.AbstractFeature;
import features.ConstraintType;

public class ExerciceTypeCardioFeature extends AbstractFeature {

    public ExerciceTypeCardioFeature() {
        super(EXERCICE_TYPE_CARDIO.getFeature(), true, true,  ConstraintType.OR, "exercise_type", "type");
    }

    public ExerciceTypeCardioFeature(boolean active) {
        super(EXERCICE_TYPE_CARDIO.getFeature(), active, true,  ConstraintType.OR, "exercise_type", "type");
    }
}