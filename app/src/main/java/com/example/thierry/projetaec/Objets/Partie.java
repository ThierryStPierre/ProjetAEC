package com.example.thierry.projetaec.Objets;

import java.sql.Time;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Partie {
    private int idPartie;
    private String lieu;
    private Time duree;
    private Equipe equipe1;
    private Equipe equipe2;
    private int pointage1;
    private int pointage2;

    public Partie(int idPartie, String lieu, Time duree, Equipe equipe1, Equipe equipe2,
                  int pointage1, int pointage2) {
        this.idPartie = idPartie;
        this.lieu = lieu;
        this.duree = duree;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.pointage1 = pointage1;
        this.pointage2 = pointage2;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "idPartie=" + idPartie +
                ", lieu='" + lieu + '\'' +
                ", duree=" + duree +
                ", equipe1=" + equipe1 +
                ", equipe2=" + equipe2 +
                ", pointage1=" + pointage1 +
                ", pointage2=" + pointage2 +
                '}';
    }

    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public int getPointage1() {
        return pointage1;
    }

    public void setPointage1(int pointage1) {
        this.pointage1 = pointage1;
    }

    public int getPointage2() {
        return pointage2;
    }

    public void setPointage2(int pointage2) {
        this.pointage2 = pointage2;
    }
}
