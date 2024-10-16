package models.domains;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import java.util.List;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CardioDTO.class, name = "CARDIO"),
        @JsonSubTypes.Type(value = StrengthDTO.class, name = "STRENGTH"),
        @JsonSubTypes.Type(value = FlexibilityDTO.class, name = "FLEXIBILITY")
})
public class ExerciceDTO {

    private UUID id;
    private String name;
    private ExerciceType type;
    private String explanation;
    private List<Media> medias;
    private Difficulty difficulty;
    private boolean isCustom;

    public ExerciceDTO() {

    }

    public ExerciceDTO(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.explanation = explanation;
        this.medias = medias;
        this.difficulty = difficulty;
        this.isCustom = isCustom;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExerciceType getType() {
        return type;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isCustom() { return isCustom; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setType(ExerciceType type) {
        this.type = type;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCustom(boolean custom) {
        this.isCustom = custom;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", explanation='" + explanation + '\'' +
                ", medias=" + medias +
                ", difficulty=" + difficulty +
                ", isCustom=" + isCustom +
                '}';
    }
}
