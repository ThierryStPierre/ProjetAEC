package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class StatsJoueur {
    private int idSaison;
    private int idJoueur;
    private int but;
    private int passe;
    private int penalite;
    private int butTireDeBarrage;

    public StatsJoueur(int idSaison, int idJoueur, int but, int passe, int penalite,
                       int butTireDeBarrage) {
        this.idSaison = idSaison;
        this.idJoueur = idJoueur;
        this.but = but;
        this.passe = passe;
        this.penalite = penalite;
        this.butTireDeBarrage = butTireDeBarrage;
    }

    @Override
    public String toString() {
        return "StatsJoueur{" +
                "idSaison=" + idSaison +
                ", idJoueur=" + idJoueur +
                ", but=" + but +
                ", passe=" + passe +
                ", penalite=" + penalite +
                ", butTireDeBarrage=" + butTireDeBarrage +
                '}';
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public int getPenalite() {
        return penalite;
    }

    public void setPenalite(int penalite) {
        this.penalite = penalite;
    }

    public int getButTireDeBarrage() {
        return butTireDeBarrage;
    }

    public void setButTireDeBarrage(int butTireDeBarrage) {
        this.butTireDeBarrage = butTireDeBarrage;
    }
}
