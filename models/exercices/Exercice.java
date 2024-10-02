package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.Type;
import java.util.List;

public class Exercice {

    private int id;
    private String name;
    private Type type;
    private String explanation;
    private List<Media> medias;
    private String performance;
    private Difficulty difficulty;

    public Exercice(int id, String name, Type type, String explanation, List<Media> medias, String performance, Difficulty difficulty) {
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

    public Type getType() {
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



}
