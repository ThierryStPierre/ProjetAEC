package com.example.thierry.projetaec.Objets;

import java.util.Date;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Saison {
    private int idSaison;
    private int idLigue;
    private Date dateDebut;
    private Date dateFin;
    private int numeroSaison;

    public Saison(int idSaison, int idLigue, Date dateDebut, Date dateFin, int numeroSaison) {
        this.idSaison = idSaison;
        this.idLigue = idLigue;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numeroSaison = numeroSaison;
    }

    @Override
    public String toString() {
        return "Saison{" +
                "idSaison=" + idSaison +
                ", idLigue=" + idLigue +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", numeroSaison=" + numeroSaison +
                '}';
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdLigue() {
        return idLigue;
    }

    public void setIdLigue(int idLigue) {
        this.idLigue = idLigue;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNumeroSaison() {
        return numeroSaison;
    }

    public void setNumeroSaison(int numeroSaison) {
        this.numeroSaison = numeroSaison;
    }
}
