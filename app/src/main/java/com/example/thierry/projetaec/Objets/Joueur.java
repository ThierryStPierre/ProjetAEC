package com.example.thierry.projetaec.Objets;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Joueur implements Parcelable{
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

    public Joueur(Parcel in){
        this.nom = in.readString();
        this.prenom = in.readString();
        this.dateNaissance = new Date(in.readLong());
        this.telephone = in.readString();
        this.courriel = in.readString();
        this.numeroChandail = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeLong(dateNaissance.getTime());
        dest.writeString(telephone);
        dest.writeString(courriel);
        dest.writeInt(numeroChandail);


    }
    public static final Parcelable.Creator<Joueur> CREATOR = new Parcelable.Creator<Joueur>(){
        @Override
        public Joueur createFromParcel(Parcel source) { return new Joueur(source);
        }

        @Override
        public Joueur[] newArray(int size)
        {
            return new Joueur[size];
        }
    };
}
