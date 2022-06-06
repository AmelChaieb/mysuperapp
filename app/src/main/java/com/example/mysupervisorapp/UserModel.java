package com.example.mysupervisorapp;

public class UserModel {

    String email, name, password, phoneNo, statut,numGroupe,nomAtelier,nomChefEquipe, surl;

    UserModel(){

    }

    public UserModel(String email, String name, String password, String phoneNo, String statut, String numGroupe, String nomAtelier, String nomChefEquipe, String surl) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.statut = statut;
        this.numGroupe = numGroupe;
        this.nomAtelier = nomAtelier;
        this.nomChefEquipe = nomChefEquipe;
        this.surl = surl;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNumGroupe() {
        return numGroupe;
    }

    public void setNumGroupe(String numGroupe) {
        this.numGroupe = numGroupe;
    }

    public String getNomAtelier() {
        return nomAtelier;
    }

    public void setNomAtelier(String nomAtelier) {
        this.nomAtelier = nomAtelier;
    }

    public String getNomChefEquipe() {
        return nomChefEquipe;
    }

    public void setNomChefEquipe(String nomChefEquipe) {
        this.nomChefEquipe = nomChefEquipe;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
