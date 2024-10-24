package models.domains;

import java.util.UUID;

public class PerformanceDTO {

    private String text;
    private UUID userId;
    private UUID exerciceId;

    public PerformanceDTO() {}

    public PerformanceDTO(String text, UUID userId, UUID exerciceId) {
        this.text = text;
        this.userId = userId;
        this.exerciceId = exerciceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
