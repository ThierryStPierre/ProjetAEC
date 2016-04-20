package com.example.thierry.projetaec.Objets;

import java.sql.Date;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Joueur {
    private int idJoueur;
//    private int idEquipe;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String telephone;
    private String courriel;
    private int numeroChandail;

    public Joueur(int idJoueur, /*int idEquipe,*/ String nom, String prenom, int numeroChandail){
        this(idJoueur, nom, prenom, null, "", "", numeroChandail);
    }

    public Joueur(int idJoueur, /*int idEquipe,*/ String nom, String prenom, Date dateNaissance,
                  String telephone, String courriel, int numeroChandail) {
        this.idJoueur = idJoueur;
//        this.idEquipe = idEquipe;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.courriel = courriel;
        this.numeroChandail = numeroChandail;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "idJoueur=" + idJoueur +
//                ", idEquipe=" + idEquipe +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", telephone='" + telephone + '\'' +
                ", courriel='" + courriel + '\'' +
                '}';
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
/*
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public int getNumeroChandail() {
        return numeroChandail;
    }

    public void setNumeroChandail(int numeroChandail) {
        this.numeroChandail = numeroChandail;
    }
}
