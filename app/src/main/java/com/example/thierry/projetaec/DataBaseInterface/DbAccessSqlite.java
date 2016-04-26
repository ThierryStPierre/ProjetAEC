package com.example.thierry.projetaec.DataBaseInterface;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.thierry.projetaec.Objets.Competence;
import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.Evenement;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.Objets.LoginObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thierry on 12/04/16.
 */
public class DbAccessSqlite extends DbAccess{

    static SQLiteDatabase db = null;

    public DbAccessSqlite(Context cntx){
        if(db == null)
            db = (new DatabaseHelper(cntx)).getReadableDatabase();
    }

    @Override
    public List<Joueur> getListJoueurs() {
        return null;
    }

    @Override
    public ArrayList<Joueur> getListJoueursParEquipe(int idEquipe/*, int idSaison*/) {
        return null;
    }

    @Override
    public List<Joueur> getListJoueursParLigue(int idLigue) {
        return null;
    }

    @Override
    public List<Ligue> getListLigues(int idGestionnaire) {
        return null;
    }

    @Override
    public List<Ligue> getListAccreditedLigues(int idMarqueur) {
        return null;
    }

    @Override
    public List<Equipe> getListEquipes(int idLigue) {
        return null;
    }

    @Override
    public List<Joueur> getListGestionnaires() {
        return null;
    }

    @Override
    public LoginObject validateLogin(String user, String pass) {
        return null;
    }

    public int ecritEvenement(Evenement e){
        return 0;
    }

    public void saveJoueur(Joueur joueur){
        ContentValues values = new ContentValues();
        values.put("ID_Personne", joueur.getIdJoueur());
        values.put("Nom", joueur.getNom());
        values.put("Prenom", joueur.getPrenom());
        values.put("ID_Equipe", joueur.getIdJoueur());
        values.put("NumeroChandail", joueur.getNumeroChandail());
        db.insert("Personne", null, values);
    }
}
