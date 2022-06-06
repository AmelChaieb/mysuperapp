package com.example.mysupervisorapp;

public class MachineHelperClasse {

    String nbrHeure, temps_pause, temps_remplissage, etat_de_fonct, defPanne, surl, lastUserMod, atelier, idMach;
    MachineHelperClasse(){

    }


    public MachineHelperClasse(String nbrHeure, String temps_pause, String temps_remplissage, String etat_de_fonct, String defPanne, String surl, String lastUserMod, String atelier, String idMach) {
        this.nbrHeure = nbrHeure;
        this.temps_pause = temps_pause;
        this.temps_remplissage = temps_remplissage;
        this.etat_de_fonct = etat_de_fonct;
        this.defPanne = defPanne;
        this.surl = surl;
        this.lastUserMod = lastUserMod;
        this.atelier = atelier;
        this.idMach = idMach;
    }


    public String getNbrHeure() {
        return nbrHeure;
    }

    public void setNbrHeure(String nbrHeure) {
        this.nbrHeure = nbrHeure;
    }

    public String getTemps_pause() {
        return temps_pause;
    }

    public void setTemps_pause(String temps_pause) {
        this.temps_pause = temps_pause;
    }

    public String getTemps_remplissage() {
        return temps_remplissage;
    }

    public void setTemps_remplissage(String temps_remplissage) {
        this.temps_remplissage = temps_remplissage;
    }

    public String getEtat_de_fonct() {
        return etat_de_fonct;
    }

    public void setEtat_de_fonct(String etat_de_fonct) {
        this.etat_de_fonct = etat_de_fonct;
    }

    public String getDefPanne() {
        return defPanne;
    }

    public void setDefPanne(String defPanne) {
        this.defPanne = defPanne;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getLastUserMod() {
        return lastUserMod;
    }

    public void setLastUserMod(String lastUserMod) {
        this.lastUserMod = lastUserMod;
    }

    public String getAtelier() {
        return atelier;
    }

    public void setAtelier(String atelier) {
        this.atelier = atelier;
    }

    public String getIdMach() {
        return idMach;
    }

    public void setIdMach(String idMach) {
        this.idMach = idMach;
    }
}
