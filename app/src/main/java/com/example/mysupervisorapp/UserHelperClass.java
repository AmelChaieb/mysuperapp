package com.example.mysupervisorapp;

public class UserHelperClass {
    String name, statut, email, phoneNo, password,numGroupe,nomAtelier,nomChefEquipe,surl;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String statut, String email, String phoneNo, String password, String numGroupe, String nomAtelier, String nomChefEquipe, String surl) {
        this.name = name;
        this.statut = statut;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.numGroupe = numGroupe;
        this.nomAtelier = nomAtelier;
        this.nomChefEquipe = nomChefEquipe;
        this.surl = surl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
