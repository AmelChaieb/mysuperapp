package com.example.mysupervisorapp;

public class ChefAtelierHelper {
    String name,phoneNum, nomAtelier , surl;
    ChefAtelierHelper() {

    }

    public ChefAtelierHelper(String name, String phoneNum, String nomAtelier, String surl) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.nomAtelier = nomAtelier;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
