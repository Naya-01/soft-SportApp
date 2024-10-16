package models.exercices;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controllers.factories.ExerciceFactory;
import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cardio.class, name = "CARDIO"),
        @JsonSubTypes.Type(value = Strength.class, name = "STRENGTH"),
        @JsonSubTypes.Type(value = Flexibility.class, name = "FLEXIBILITY")
})
public class Exercice {

    private UUID id;
    private String name;
    private ExerciceType type;
    private String explanation;
    private List<Media> medias;
    private Difficulty difficulty;
    private boolean isCustom;

    private static final String EXERCICE_FILE_PATH = "src/main/java/data/exercices.json";
    private static final String CUSTOM_EXERCICE_FILE_PATH = "src/main/java/data/custom-exercices.json";

    public Exercice() {}

    public Exercice(UUID id, String name, ExerciceType type, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom) {
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

    public Exercice getExerciceByName(String name) {
        return JsonDBUtil.findObjectInJson(EXERCICE_FILE_PATH,"name", name, Exercice.class);
    }

    public Exercice addExercice(ExerciceType type, String name, String explanation, List<Media> medias, Difficulty difficulty, boolean isCustom, Object... extraParams) {
        Exercice exercice = ExerciceFactory.createExercice(type, name, explanation, medias, difficulty, isCustom, extraParams);
        exercice.setId(UUID.randomUUID());
        JsonDBUtil.addObjectToJson(EXERCICE_FILE_PATH, exercice, Exercice.class);
        return exercice;
    }

    public void addCustomExercice(UUID id) {
        JsonDBUtil.addObjectToJson(CUSTOM_EXERCICE_FILE_PATH, new CustomExercice(UserSessionManager.currentUser.getId(), id), CustomExercice.class);
    }

    public List<Exercice> getAllExercices() {
        return JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
    }

    public List<Exercice> getExercicesByTypeAndDifficulty(Difficulty difficulty, ExerciceType exerciceType) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        return exercices.stream()
                .filter(exercice -> exercice.getDifficulty() != null && exercice.getDifficulty().equals(difficulty) &&
                        exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public List<Exercice> getExercicesByType(ExerciceType exerciceType) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        return exercices.stream()
                .filter(exercice -> exercice.getType() != null && exercice.getType().equals(exerciceType))
                .toList();
    }

    public boolean deleteExercice(String id) {
        List<Exercice> exercices = JsonDBUtil.readFromJson(EXERCICE_FILE_PATH, Exercice.class);
        boolean removed = exercices.removeIf(exercice -> exercice.getId().equals(UUID.fromString(id)));
        if (removed) {
            JsonDBUtil.writeToJson(EXERCICE_FILE_PATH, exercices);
        }
        return removed;
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
