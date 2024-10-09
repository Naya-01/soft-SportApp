package models.enums;

public enum ExerciceType {

    CARDIO("Cardio"),
    STRENGTH("Strength"),
    FLEXIBILITY("Flexibility");

    private String type;

    ExerciceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
