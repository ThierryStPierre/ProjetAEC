package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Point {
    private int idEquipe;
    private int idJoueur;
    private int idSaison;
    private int idPartie;
    private String type;

    public Point(int idEquipe, int idJoueur, int idSaison, int idPartie, String type) {
        this.idEquipe = idEquipe;
        this.idJoueur = idJoueur;
        this.idSaison = idSaison;
        this.idPartie = idPartie;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "idEquipe=" + idEquipe +
                ", idJoueur=" + idJoueur +
                ", idSaison=" + idSaison +
                ", idPartie=" + idPartie +
                ", type='" + type + '\'' +
                '}';
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
