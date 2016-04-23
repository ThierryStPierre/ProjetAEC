package com.example.thierry.projetaec.Objets;

/**
 * Created by thierry on 16-04-22.
 */
public class Evenement {
    int idBut;          // id de la personne marquant le but, donc son équipe
    int idPasse;        // id de la personne qui assiste (s'il y en a)
    int idPartie;       // id de la partie en cour, inclue la saison
    int idPenalite;     // id de la personne recevant une pénalité
    int idLancer;        // id du gardien faisant un arrêt.

    public Evenement( int but, int passe, int partie, int penalite, int lancer){
        idBut = but;
        idPasse = passe;
        idPartie = partie;
        idPenalite = penalite;
        idLancer = lancer;
    }

    public int getIdBut() {
        return idBut;
    }

    public void setIdBut(int idBut) {
        this.idBut = idBut;
    }

    public int getIdPasse() {
        return idPasse;
    }

    public void setIdPasse(int idPasse) {
        this.idPasse = idPasse;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public int getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(int idPenalite) {
        this.idPenalite = idPenalite;
    }

    public int getIdLancer() {
        return idLancer;
    }

    public void setIdLancer(int idLancer) {
        this.idLancer = idLancer;
    }
}
