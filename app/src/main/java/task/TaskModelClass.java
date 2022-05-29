package task;

public class TaskModelClass {
    String title,description,nomAtelier,nomGroup,nomChefEquipe,date;

    public TaskModelClass(String title, String description, String nomAtelier, String nomGroup, String nomChefEquipe, String date) {
        this.title = title;
        this.description = description;
        this.nomAtelier = nomAtelier;
        this.nomGroup = nomGroup;
        this.nomChefEquipe = nomChefEquipe;
        this.date = date;
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
}
