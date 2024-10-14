package models;

import models.exercices.Exercice;

public class Performance {

    private String text;
    private User user;
    private Exercice exercice;

    public Performance() {}

    public Performance(String text, User user, Exercice exercice) {
        this.text = text;
        this.user = user;
        this.exercice = exercice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }
}
