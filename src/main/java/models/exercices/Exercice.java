package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import java.util.List;

public class Exercice {

    private int id;
    private String name;
    private ExerciceType type;
    private String explanation;
    private List<Media> medias;
    private String performance;
    private Difficulty difficulty;

    public Exercice(int id, String name, ExerciceType type, String explanation, List<Media> medias, String performance, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.explanation = explanation;
        this.medias = medias;
        this.performance = performance;
        this.difficulty = difficulty;
    }

    public int getId() {
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

    public String getPerformance() {
        return performance;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
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

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
