package com.example.thierry.projetaec.Objets;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thierry on 16-04-18.
 */
public class Competence implements Parcelable {

   // public enum competenceType {NO_COMPETENCE, GESTIONNAIRE, MARQUEUR, CAPITAINE};
    int idPersonne;
    int idLigue;
    int idSousLigue;
    int idEquipe;
    int competence; // 0 = aucune competence, 1 = gestionnaire,
                    // 2 = marqueur, 3 = capitaine



    public Competence(int idP, int idL, int idS, int idE, int ct){
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

    public int getCompetence() {
        return competence;
    }

    public void setCompetence(int competence) {
        this.competence = competence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idLigue);
        dest.writeInt(idSousLigue);
        dest.writeInt(idEquipe);
        dest.writeInt(competence);
    }
    public static final Parcelable.Creator<Competence> CREATOR
            = new Parcelable.Creator<Competence>(){

        @Override
        public Competence createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Competence[] newArray(int size) {
            return new Competence[0];
        }
    };

    private Competence(Parcel in){
        idLigue = in.readInt();
        idSousLigue = in.readInt();
        idEquipe = in.readInt();
        competence = in.readInt();
    }

}

