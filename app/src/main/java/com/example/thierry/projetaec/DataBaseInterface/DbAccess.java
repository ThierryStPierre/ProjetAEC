package com.example.thierry.projetaec.DataBaseInterface;

import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.Evenement;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.Objets.Competence;
import com.example.thierry.projetaec.Objets.LoginObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thierry on 12/04/16.
 */
public  abstract class DbAccess {

    abstract public List<Joueur> getListJoueurs();
    abstract public ArrayList<Joueur> getListJoueursParEquipe(int idEquipe);
    abstract public Equipe getEquipeInfo(int idEquipe);
    abstract public List<Joueur> getListJoueursParLigue(int idLigue);
    abstract public List<Ligue> getListLigues(int idGestionnaire);
    abstract public List<Ligue> getListAccreditedLigues(int idMarqueur);
    abstract public List<Equipe> getListEquipes(int idLigue);
    abstract public List<Joueur> getListGestionnaires();
    abstract public LoginObject validateLogin(String user, String pass);
    abstract public int ecritEvenement(Evenement e);
}
