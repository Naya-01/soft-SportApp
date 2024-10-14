package models;

import models.enums.ExerciceType;

public class Coach {

    private int id;
    private String name;
    private ExerciceType exerciceType;

    public Coach(int id, String name, ExerciceType exerciceType) {
        this.id = id;
        this.name = name;
        this.exerciceType = exerciceType;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}
