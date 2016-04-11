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
    private String nomUsager;
    private String motDePasse;
    private boolean capitaine;
    private boolean pointeur;
    private boolean gestionnaire;

    public Joueur(int idJoueur, /*int idEquipe,*/ String nom, String prenom){
        this(idJoueur, nom, prenom, null, "", "", "", "", false, false, false);
    }

    public Joueur(int idJoueur, /*int idEquipe,*/ String nom, String prenom, Date dateNaissance,
                  String telephone, String courriel, String nomUsager, String motDePasse,
                  boolean capitaine, boolean pointeur, boolean gestionnaire) {
        this.idJoueur = idJoueur;
//        this.idEquipe = idEquipe;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.courriel = courriel;
        this.nomUsager = nomUsager;
        this.motDePasse = motDePasse;
        this.capitaine = capitaine;
        this.pointeur = pointeur;
        this.gestionnaire = gestionnaire;
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
                ", nomUsager='" + nomUsager + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", capitaine=" + capitaine +
                ", pointeur=" + pointeur +
                ", gestionnaire=" + gestionnaire +
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

    public String getNomUsager() {
        return nomUsager;
    }

    public void setNomUsager(String nomUsager) {
        this.nomUsager = nomUsager;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isCapitaine() {
        return capitaine;
    }

    public void setCapitaine(boolean capitaine) {
        this.capitaine = capitaine;
    }

    public boolean isPointeur() {
        return pointeur;
    }

    public void setPointeur(boolean pointeur) {
        this.pointeur = pointeur;
    }

    public boolean isGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(boolean gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
}
