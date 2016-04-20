package com.example.thierry.projetaec.Objets;

import java.util.List;

/**
 * Created by thierry on 16-04-20.
 */
public class LoginObject {
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
}
