package models;

import models.exercices.Exercice;

import java.util.List;

public class Calendar {
    private int id;
    private int year;
    private int month;
    private int day;
    private List<Exercice> exercices;
    private User user;

    public Calendar(int id, int year, int month, int day, List<Exercice> exercices) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.exercices = exercices;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }

    public void addExercice(Exercice exercice){
        this.exercices.add(exercice);
    }

    public void removeExercice(Exercice exercice){
        this.exercices.remove(exercice);
    }
}
