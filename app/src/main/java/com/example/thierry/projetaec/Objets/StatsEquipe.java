package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class StatsEquipe {
    private int idSaison;
    private int idEquipe;
    private int nombrePartie;
    private int victoire;
    private int defaite;
    private int butPour;
    private int butContre;
    private int penalite;

    public StatsEquipe(int idSaison, int idEquipe, int nombrePartie, int victoire, int defaite,
                       int butPour, int butContre, int penalite) {
        this.idSaison = idSaison;
        this.idEquipe = idEquipe;
        this.nombrePartie = nombrePartie;
        this.victoire = victoire;
        this.defaite = defaite;
        this.butPour = butPour;
        this.butContre = butContre;
        this.penalite = penalite;
    }

    @Override
    public String toString() {
        return "StatsEquipe{" +
                "idSaison=" + idSaison +
                ", idEquipe=" + idEquipe +
                ", nombrePartie=" + nombrePartie +
                ", victoire=" + victoire +
                ", defaite=" + defaite +
                ", butPour=" + butPour +
                ", butContre=" + butContre +
                ", penalite=" + penalite +
                '}';
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getNombrePartie() {
        return nombrePartie;
    }

    public void setNombrePartie(int nombrePartie) {
        this.nombrePartie = nombrePartie;
    }

    public int getVictoire() {
        return victoire;
    }

    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }

    public int getDefaite() {
        return defaite;
    }

    public void setDefaite(int defaite) {
        this.defaite = defaite;
    }

    public int getButPour() {
        return butPour;
    }

    public void setButPour(int butPour) {
        this.butPour = butPour;
    }

    public int getButContre() {
        return butContre;
    }

    public void setButContre(int butContre) {
        this.butContre = butContre;
    }

    public int getPenalite() {
        return penalite;
    }

    public void setPenalite(int penalite) {
        this.penalite = penalite;
    }
}
