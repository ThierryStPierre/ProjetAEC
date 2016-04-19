package com.example.thierry.projetaec.Objets;

/**
 * Created by thierry on 16-04-18.
 */
public class Competence {

    public enum competenceType {NO_COMPETENCE, GESTIONNAIRE, MARQUEUR, CAPITAINE};
    int idPersonne;
    int idLigue;
    int idSousLigue;
    int idEquipe;
    competenceType competence;

    public Competence(int idP, int idL, int idS, int idE, competenceType ct){
        idPersonne  = idP;
        idLigue = idL;
        idSousLigue = idS;
        idEquipe = idE;
        competence = ct;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getIdLigue() {
        return idLigue;
    }

    public void setIdLigue(int idLigue) {
        this.idLigue = idLigue;
    }

    public int getIdSousLigue() {
        return idSousLigue;
    }

    public void setIdSousLigue(int idSousLigue) {
        this.idSousLigue = idSousLigue;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public competenceType getCompetence() {
        return competence;
    }

    public void setCompetence(competenceType competence) {
        this.competence = competence;
    }
}
