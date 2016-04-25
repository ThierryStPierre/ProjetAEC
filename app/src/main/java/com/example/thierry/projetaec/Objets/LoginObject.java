package com.example.thierry.projetaec.Objets;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thierry on 16-04-20.
 */
public class LoginObject implements Parcelable {
    int loginId;
    String nom;
    String prenom;
    List<Competence> competences;

    public LoginObject(int id, String n, String p){
        loginId = id;
        nom = n;
        prenom = p;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

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

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loginId);
        dest.writeString(nom);
        dest.writeString(prenom);
       // dest.writeList(competences);
    }
    public static final Parcelable.Creator<LoginObject> CREATOR
            = new Parcelable.Creator<LoginObject>(){

        @Override
        public LoginObject createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public LoginObject[] newArray(int size) {
            return new LoginObject[0];
        }
    };
    private LoginObject(Parcel in){
        loginId = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        competences = new ArrayList<Competence>();
        competences = in.readArrayList(Competence.class.getClassLoader());
    }
}
