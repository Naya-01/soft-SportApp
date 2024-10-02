package models.enums;

public enum Type {

    CARDIO("Cardio"),
    STRENGTH("Strength"),
    FLEXIBILITY("Flexibility");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
