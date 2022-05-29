package com.example.mysupervisorapp;

public class MachineModel
{
    String id_machine, lastUserMod,nbrHeure, temps_pause, temps_remplissage,defPanne, etat_de_fonct;


    MachineModel(){

    }

    public MachineModel(String id_machine, String lastUserMod, String nbrHeure, String temps_pause, String temps_remplissage, String defPanne, String etat_de_fonct) {
        this.id_machine = id_machine;
        this.lastUserMod = lastUserMod;
        this.nbrHeure = nbrHeure;
        this.temps_pause = temps_pause;
        this.temps_remplissage = temps_remplissage;
        this.defPanne = defPanne;
        this.etat_de_fonct = etat_de_fonct;
    }

    public String getId_machine() {
        return id_machine;
    }

    public void setId_machine(String id_machine) {
        this.id_machine = id_machine;
    }

    public String getLastUserMod() {
        return lastUserMod;
    }

    public void setLastUserMod(String lastUserMod) {
        this.lastUserMod = lastUserMod;
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

    public String getDefPanne() {
        return defPanne;
    }

    public void setDefPanne(String defPanne) {
        this.defPanne = defPanne;
    }

    public String getEtat_de_fonct() {
        return etat_de_fonct;
    }

    public void setEtat_de_fonct(String etat_de_fonct) {
        this.etat_de_fonct = etat_de_fonct;
    }
}
