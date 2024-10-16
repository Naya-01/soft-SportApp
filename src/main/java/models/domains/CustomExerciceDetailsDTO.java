package models.domains;

public class CustomExerciceDetailsDTO {

    private UserDTO user;
    private ExerciceDTO exercice;

    public CustomExerciceDetailsDTO() {}

    public CustomExerciceDetailsDTO(UserDTO user, ExerciceDTO exercice) {
        this.user = user;
        this.exercice = exercice;
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
