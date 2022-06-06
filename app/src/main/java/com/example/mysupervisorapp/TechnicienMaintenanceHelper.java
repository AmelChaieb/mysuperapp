package com.example.mysupervisorapp;

public class TechnicienMaintenanceHelper {
    String name,phoneNum, surl;

    TechnicienMaintenanceHelper(){

    }

    public TechnicienMaintenanceHelper(String name, String phoneNum, String surl) {
        this.name = name;
        this.phoneNum = phoneNum;
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

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
