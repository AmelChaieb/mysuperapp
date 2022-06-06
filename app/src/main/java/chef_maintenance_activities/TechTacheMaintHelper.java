package chef_maintenance_activities;

public class TechTacheMaintHelper {

    String completer_par,nomAtelier ,textTache ,date ,warningtxt;

    TechTacheMaintHelper(){

    }

    public TechTacheMaintHelper(String completer_par, String nomAtelier, String textTache, String date, String warningtxt) {
        this.completer_par = completer_par;
        this.nomAtelier = nomAtelier;
        this.textTache = textTache;
        this.date = date;
        this.warningtxt = warningtxt;
    }


    public String getCompleter_par() {
        return completer_par;
    }

    public void setCompleter_par(String completer_par) {
        this.completer_par = completer_par;
    }

    public String getNomAtelier() {
        return nomAtelier;
    }

    public void setNomAtelier(String nomAtelier) {
        this.nomAtelier = nomAtelier;
    }

    public String getTextTache() {
        return textTache;
    }

    public void setTextTache(String textTache) {
        this.textTache = textTache;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWarningtxt() {
        return warningtxt;
    }

    public void setWarningtxt(String warningtxt) {
        this.warningtxt = warningtxt;
    }
}
