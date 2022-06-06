package com.example.mysupervisorapp;

public class ChefEquipeHelper {
    String name,phoneNo, nomAtelier , surl;

    ChefEquipeHelper(){

    }

    public ChefEquipeHelper(String name, String phoneNo, String nomAtelier, String surl) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.nomAtelier = nomAtelier;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNomAtelier() {
        return nomAtelier;
    }

    public void setNomAtelier(String nomAtelier) {
        this.nomAtelier = nomAtelier;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
