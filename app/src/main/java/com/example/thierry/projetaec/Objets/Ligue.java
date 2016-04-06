package com.example.thierry.projetaec.Objets;

/**
 * Created by jonathan on 2016-04-06.
 */
public class Ligue {
    private int idLigue;
    /*gestionnaire?*/
    private String nomLigue;

    public Ligue(int idLigue, String nomLigue) {
        this.idLigue = idLigue;
        this.nomLigue = nomLigue;
    }

    @Override
    public String toString() {
        return "Ligue{" +
                "idLigue=" + idLigue +
                ", nomLigue='" + nomLigue + '\'' +
                '}';
    }

    public int getIdLigue() {
        return idLigue;
    }

    public void setIdLigue(int idLigue) {
        this.idLigue = idLigue;
    }

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }
}
