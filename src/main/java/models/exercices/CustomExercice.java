package models.exercices;

import java.util.UUID;

public class CustomExercice {

    private UUID userId;
    private UUID exerciceId;

    public CustomExercice() {}

    public CustomExercice(UUID userId, UUID exerciceId) {
        this.userId = userId;
        this.exerciceId = exerciceId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(UUID exerciceId) {
        this.exerciceId = exerciceId;
    }
}
