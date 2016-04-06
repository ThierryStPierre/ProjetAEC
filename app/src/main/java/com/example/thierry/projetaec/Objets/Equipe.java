package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Equipe {
    private int idEquipe;
    private int idLigue;
    private String nomEquipe;

    public Equipe(int idEquipe, int idLigue, String nomEquipe) {
        this.idEquipe = idEquipe;
        this.idLigue = idLigue;
        this.nomEquipe = nomEquipe;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "idEquipe=" + idEquipe +
                ", idLigue=" + idLigue +
                ", nomEquipe='" + nomEquipe + '\'' +
                '}';
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdLigue() {
        return idLigue;
    }

    public void setIdLigue(int idLigue) {
        this.idLigue = idLigue;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
}
