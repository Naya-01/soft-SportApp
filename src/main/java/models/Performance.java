package models;

import models.domains.ExerciceDTO;
import models.domains.UserDTO;

public class Performance {

    private String text;
    private UserDTO user;
    private ExerciceDTO exercice;

    public Performance() {}

    public Performance(String text, UserDTO user, ExerciceDTO exercice) {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ExerciceDTO getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceDTO exercice) {
        this.exercice = exercice;
    }
}
