package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Alignement {
    private int idJoueur;
    private int idEquipe;
    private int idSaison;
    private String position;
    private int numeroChandail;

    public Alignement(int idJoueur, int idEquipe, int idSaison, String position,
                      int numeroChandail) {
        this.idJoueur = idJoueur;
        this.idEquipe = idEquipe;
        this.idSaison = idSaison;
        this.position = position;
        this.numeroChandail = numeroChandail;
    }

    @Override
    public String toString() {
        return "Alignement{" +
                "idJoueur=" + idJoueur +
                ", idEquipe=" + idEquipe +
                ", idSaison=" + idSaison +
                ", position='" + position + '\'' +
                ", numeroChandail=" + numeroChandail +
                '}';
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumeroChandail() {
        return numeroChandail;
    }

    public void setNumeroChandail(int numeroChandail) {
        this.numeroChandail = numeroChandail;
    }
}
