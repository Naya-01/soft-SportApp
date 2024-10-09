package models;

import models.enums.ExerciceType;

public class Coach {
    private String name;
    private ExerciceType exerciceType;

    public Coach(String name, ExerciceType exerciceType) {
        this.name = name;
        this.exerciceType = exerciceType;
    }

    public String getName() {
        return name;
    }

    public ExerciceType getExerciceType() {
        return exerciceType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExerciceType(ExerciceType exerciceType) {
        this.exerciceType = exerciceType;
    }

}
