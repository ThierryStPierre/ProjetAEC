package com.example.thierry.projetaec.DataBaseInterface;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 201596474 on 2016-03-09.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gestionContact";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Création des tables
/*        String sql = "CREATE TABLE IF NOT EXISTS Partie (" +
                "ID_Partie INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Lieu TEXT, " +
                "Duree Integer, " +
                "Equipe1 Integer, " +
                "Equipe2 Integer, " +
                "Pointage1 Integer, " +
                "Pointage2 Integer)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Alignement (" +
                "ID_Joueur Integer, " +
                "ID_Equipe Integer, " +
                "ID_Saison Integer, " +
                "Position text, " +
                "Numero_Chandail Integer)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Equipe (" +
                "ID_Equipe INTEGER," +
                "ID_Ligue Integer, " +
                "ID_Ligue text)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Personne (" +
                "ID_Personne INTEGER," +
                "Nom text, " +
                "Prenom text)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Joueur (" +
                "ID_Personne INTEGER," +
                "Nom text, " +
                "Prenom text,"+
                "NumeroChandail INTEGER,"+
                "ID_Equipe INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Ligue (" +
                "ID_Ligue INTEGER," +
                "ID_Personne INTEGER," +
                "Nom_Ligue text)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Competence (" +
                "ID_Personne INTEGER," +
                "ID_Ligue INTEGER," +
                "ID_SousLigue INTEGER," +
                "ID_Equipe INTEGER," +
                "ID_Saison INTEGER," +
                "competence INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Evenement(" +
                "ID_PersonneBut INTEGER," +
                "ID_PersonnePasse INTEGER," +
                "ID_Partie INTEGER NOT NULL," +
                "ID_PersonnePenalite INTEGER," +
                "ID_PersonneTire INTEGER)";
        db.execSQL(sql);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Partie");
        db.execSQL("DROP TABLE IF EXISTS Alignement");
        db.execSQL("DROP TABLE IF EXISTS Equipe");
        db.execSQL("DROP TABLE IF EXISTS Personne");
        db.execSQL("DROP TABLE IF EXISTS Evenement");
        db.execSQL("DROP TABLE IF EXISTS Ligue");
        db.execSQL("DROP TABLE IF EXISTS Competence");
        onCreate(db);
    }
}
