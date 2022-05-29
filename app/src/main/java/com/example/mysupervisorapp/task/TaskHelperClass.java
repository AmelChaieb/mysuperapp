package com.example.mysupervisorapp.task;
public class TaskHelperClass {
    String title,description,nomAtelier,nomGroup,nomChefEquipe,date, month, day ;


    TaskHelperClass(){

    }

    public TaskHelperClass(String title, String description, String nomAtelier, String nomGroup, String nomChefEquipe, String date, String month, String day) {
        this.title = title;
        this.description = description;
        this.nomAtelier = nomAtelier;
        this.nomGroup = nomGroup;
        this.nomChefEquipe = nomChefEquipe;
        this.date = date;
        this.month = month;
        this.day = day;
//        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomAtelier() {
        return nomAtelier;
    }

    public void setNomAtelier(String nomAtelier) {
        this.nomAtelier = nomAtelier;
    }

    public String getNomGroup() {
        return nomGroup;
    }

    public void setNomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    public String getNomChefEquipe() {
        return nomChefEquipe;
    }

    public void setNomChefEquipe(String nomChefEquipe) {
        this.nomChefEquipe = nomChefEquipe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
