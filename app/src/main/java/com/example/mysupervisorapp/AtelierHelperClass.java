package com.example.mysupervisorapp;

public class AtelierHelperClass {
    String nom_chef_atelier,nomA,nombre_equipes,nombre_machines ,surl;

    AtelierHelperClass(){

    }


    public AtelierHelperClass(String nom_chef_atelier, String nomA, String nombre_equipes, String nombre_machines, String surl) {
        this.nom_chef_atelier = nom_chef_atelier;
        this.nomA = nomA;
        this.nombre_equipes = nombre_equipes;
        this.nombre_machines = nombre_machines;
        this.surl = surl;
    }

    public String getNom_chef_atelier() {
        return nom_chef_atelier;
    }

    public void setNom_chef_atelier(String nom_chef_atelier) {
        this.nom_chef_atelier = nom_chef_atelier;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public String getNombre_equipes() {
        return nombre_equipes;
    }

    public void setNombre_equipes(String nombre_equipes) {
        this.nombre_equipes = nombre_equipes;
    }

    public String getNombre_machines() {
        return nombre_machines;
    }

    public void setNombre_machines(String nombre_machines) {
        this.nombre_machines = nombre_machines;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
