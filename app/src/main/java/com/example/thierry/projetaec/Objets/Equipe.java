package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Equipe {
    private int idEquipe;
    private int idLigue;
    private int idSousLigue;
    private String nomEquipe;

    public Equipe(int idEquipe, int idLigue, String nomEquipe) {
        this(idEquipe, idLigue, -1, nomEquipe);
    }

    public Equipe(int idEquipe, int idLigue, int idSousLigue, String nomEquipe) {
        this.idEquipe = idEquipe;
        this.idLigue = idLigue;
        this.idSousLigue = idSousLigue;
        this.nomEquipe = nomEquipe;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "idEquipe=" + idEquipe +
                ", idLigue=" + idLigue +
                ", idSousLigue=" + idSousLigue +
                ", nomEquipe='" + nomEquipe + '\'' +
                '}';
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public int getIdSousLigue() {
        return idSousLigue;
    }

    public void setIdSousLigue(int idSousLigue) {
        this.idSousLigue = idSousLigue;
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
