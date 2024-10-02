package models.exercices;

import models.Media;
import models.enums.Difficulty;
import models.enums.Type;

import java.util.List;

public class Flexibility extends Exercice {

    public Flexibility(int id, String name, Type type, String explanation, List<Media> medias, String performance, Difficulty difficulty) {
        super(id, name, type, explanation, medias, performance, difficulty);
    }
}
