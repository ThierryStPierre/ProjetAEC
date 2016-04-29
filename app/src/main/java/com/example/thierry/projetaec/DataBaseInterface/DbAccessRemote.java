package com.example.thierry.projetaec.DataBaseInterface;

import android.content.Context;
import android.widget.Toast;

import com.example.thierry.projetaec.Objets.Competence;
import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.Evenement;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.Objets.LoginObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thierry on 12/04/16.
 */
public class DbAccessRemote extends DbAccess{
    public static final String strURLRd = "http://thierrystpierre.ddns.net:81/ProjetAEC/limuxReader.php";
    public static final String strURLWt = "http://thierrystpierre.ddns.net:81/ProjetAEC/limuxWriter.php";
    public static final String strURLUd = "http://thierrystpierre.ddns.net:81/ProjetAEC/limuxUpdater.php";
    private List<NameValuePair> paramRequete;
    private JSONParser parser;
    private String ligneResult = null;
    public volatile boolean parsingComplete = true;
    DbAccessSqlite localBKP = null;
    private String url;

    enum DbAction {DB_READ, DB_WRITE, DB_UPDATE};
    public DbAccessRemote(Context cntx){

    }

    void setLocalBackUp(DbAccessSqlite dbaLite){
        localBKP = dbaLite;
    }

    private void sendRequest(List<NameValuePair> pairs, DbAction action_RdWr){
        paramRequete = pairs;
        ligneResult = null;
        parsingComplete = false;
        if(action_RdWr == DbAction.DB_READ)
            url = strURLRd;
        else
            url = strURLWt;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(url);
                    httppost.setEntity(new UrlEncodedFormEntity(paramRequete));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity2 = response.getEntity();
                    ligneResult = EntityUtils.toString(entity2);
                    parsingComplete = true;
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public List<Joueur> getListJoueurs() {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeAllJoueur"));

        parsingComplete = false;
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if(status.equalsIgnoreCase("Success")) {
                    JSONArray joueurArray = parser.getList("Alignement");
                    if (joueurArray != null) {
                        liste = new ArrayList<Joueur>();
                        for (int index = 0; index < joueurArray.length(); index++) {
                            try {
                                System.out.println("ca rentre ou pas dans dbaccessRemote");
                                JSONObject jsonObject = joueurArray.getJSONObject(index);
                                Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                        -1,
                                        jsonObject.getString("nom"),
                                        jsonObject.getString("prenom"),
                                        jsonObject.getInt("numeroChandail"));
                                liste.add(joueur);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return liste;
    }

    public List<Joueur> getListJoueursParLigue(int idLigue) {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeJoueurLigue"));
        pairs.add(new BasicNameValuePair("idLigue", "" + idLigue));
        parsingComplete = false;
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if(status.equalsIgnoreCase("Success")) {
                    JSONArray joueurArray = parser.getList("Alignement");
                    if (joueurArray != null) {
                        liste = new ArrayList<Joueur>();
                        for (int index = 0; index < joueurArray.length(); index++) {
                            try {
                                JSONObject jsonObject = joueurArray.getJSONObject(index);
                                Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                        -1,
                                        jsonObject.getString("nom"),
                                        jsonObject.getString("prenom"),
                                        jsonObject.getInt("numeroChandail"));
                                liste.add(joueur);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return liste;
    }

    public ArrayList<Joueur> getListJoueursParEquipe(int idEquipe) {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeJoueur"));
        pairs.add(new BasicNameValuePair("idEquipe", ""+idEquipe));
        parsingComplete = false;
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {

                if(status.equalsIgnoreCase("Success")) {

                    JSONArray joueurArray = parser.getList("Alignement");
                    if (joueurArray != null) {

                        liste = new ArrayList<Joueur>();
                        for (int index = 0; index < joueurArray.length(); index++) {

                            try {
                                JSONObject jsonObject = joueurArray.getJSONObject(index);
                                Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                        idEquipe,
                                        jsonObject.getString("nom"),
                                        jsonObject.getString("prenom"),
                                        jsonObject.getInt("numeroChandail"));
//                                localBKP.saveJoueur(joueur);
                                liste.add(joueur);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return liste;
    }

    public List<Ligue> getListLigues(int idGestionnaire) {
        ArrayList<Ligue> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeLigue"));
        if(idGestionnaire > 0)
            pairs.add(new BasicNameValuePair("idGestionnaire", "" + idGestionnaire));
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if(status.equalsIgnoreCase("Success")) {
                    System.out.println("on rentre dans caseIgnore");
                    JSONArray ligueArray = parser.getList("Ligues");
                    if (ligueArray != null) {
                        liste = new ArrayList<Ligue>();
                        for (int index = 0; index < ligueArray.length(); index++) {
                            try {
                                JSONObject jsonObject = ligueArray.getJSONObject(index);
                                Ligue ligue = new Ligue(jsonObject.getInt("id"),
                                        jsonObject.getString("nom")/*,
                                                            jsonObject.getInt("idOwnder")*/);
                                liste.add(ligue);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return liste;
    }

    public List<Equipe> getListEquipes(int idLigue) {
        ArrayList<Equipe> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeEquipe"));
        pairs.add(new BasicNameValuePair("idLigue", "" + idLigue));
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if(status.equalsIgnoreCase("Success")) {
                    JSONArray ligueArray = parser.getList("Equipes");
                    System.out.print("DataBaseFront.getListEquipes() ligueArray  = " + ligueArray + "\n\n");
                    System.out.flush();
                    if (ligueArray != null) {
                        liste = new ArrayList<Equipe>();
                        for (int index = 0; index < ligueArray.length(); index++) {
                            try {
                                JSONObject jsonObject = ligueArray.getJSONObject(index);
                                Equipe equipe = new Equipe(jsonObject.getInt("id"),
                                        idLigue,
                                        jsonObject.getString("nom"));
                                liste.add(equipe);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return liste;
    }

    public List<Joueur> getListGestionnaires() {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeGestionnaires"));
        parsingComplete = false;
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if (status.equalsIgnoreCase("Success")) {
                    JSONArray joueurArray = parser.getList("Gestionnaires");
                    if (joueurArray != null) {
                        liste = new ArrayList<Joueur>();
                        for (int index = 0; index < joueurArray.length(); index++) {
                            try {
                                JSONObject jsonObject = joueurArray.getJSONObject(index);
                                Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                        -1,
                                        jsonObject.getString("nom"),
                                        jsonObject.getString("prenom"), 0);
                                liste.add(joueur);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return liste;
    }

    public List<Ligue> getListAccreditedLigues(int idMarqueur){
        ArrayList<Ligue> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeLigueParMarqueur"));
        pairs.add(new BasicNameValuePair("idMarqueur", "" + idMarqueur));
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if (status.equalsIgnoreCase("Success")) {
                    JSONArray ligueArray = parser.getList("Ligues");
                    if (ligueArray != null) {
                        liste = new ArrayList<Ligue>();
                        for (int index = 0; index < ligueArray.length(); index++) {
                            try {
                                JSONObject jsonObject = ligueArray.getJSONObject(index);
                                Ligue ligue = new Ligue(jsonObject.getInt("id"),
                                        jsonObject.getString("nom")/*,
                                                            jsonObject.getInt("idOwnder")*/);
                                liste.add(ligue);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return liste;
    }

    public LoginObject validateLogin(String user, String pass){
        System.out.print("DbAccessRemote validateLogin  user= " + user + " pass= " + pass + "\n\n");
        System.out.flush();
        LoginObject lObj = null;
        ArrayList<Competence> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "validateLogin"));
        pairs.add(new BasicNameValuePair("userName", user));
        pairs.add(new BasicNameValuePair("passWord", pass));
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            System.out.print("DbAccessRemote validateLogin ...(?)");
            System.out.flush();
            String status = parser.getStatus();
            System.out.print("DbAccessRemote validateLogin -- " + status + " !!");
            System.out.flush();
            if (!status.isEmpty()) {
                if (status.equalsIgnoreCase("Success")) {


                    JSONObject personObject = parser.getJSONObject("personne");
                    System.out.print("DbAccessRemote validateLogin - personArray : " + personObject + " !!\n\n");
                    System.out.flush();
                    if (personObject != null) {
                        try {
                            int logId = personObject.getInt("Id");
                            String nom = personObject.getString("nom");
                            String prenom = personObject.getString("prenom");
                            lObj = new LoginObject(logId, nom, prenom);

                            System.out.print("DbAccessRemote validateLogin - LoginObject : " + lObj + " !!\n\n");
                            System.out.flush();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    JSONArray ligueArray = parser.getList("competence");
                    if (ligueArray != null) {
                        liste = new ArrayList<Competence>();
                        for (int index = 0; index < ligueArray.length(); index++) {
                            try {
                                JSONObject jsonObject = ligueArray.getJSONObject(index);
                                String comp = jsonObject.getString("competenceValue");
                                String sousLigue = jsonObject.getString("sous-ligue");
                                String equipe = jsonObject.getString("equipe");
                                int idLigue = jsonObject.getInt("ligue");
                                int idSousLigue = -1;
                                int idEquipe = -1;
                                int typeComp = 0;//Competence.competenceType.NO_COMPETENCE;
                                switch (comp) {
                                    case "Gestionnaire":
                                        typeComp = 1;//Competence.competenceType.GESTIONNAIRE;
                                        break;
                                    case "Capitaine":
                                        typeComp = 2;//Competence.competenceType.CAPITAINE;
                                        break;
                                    case "Marqueur":
                                        typeComp = 3;//Competence.competenceType.MARQUEUR;
                                        break;
                                }
                                System.out.print("DbAccessRemote validateLogin equipe : " + equipe + ", sousLigue : " + sousLigue + " !!");
                                System.out.flush();
                                if (!equipe.isEmpty()) {
                                    idEquipe = Integer.parseInt(equipe);
                                }
                                if (!sousLigue.isEmpty())
                                    idSousLigue = Integer.parseInt(sousLigue);
                                Competence competence = new Competence(jsonObject.getInt("id"),
                                        idLigue, idSousLigue, idEquipe, typeComp);
                                liste.add(competence);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (lObj != null)
                    lObj.setCompetences(liste);
            }
        }
        return lObj;
    }

    public int ecritEvenement(Evenement e){
        int newIdx = -1;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "newEvent"));
        pairs.add(new BasicNameValuePair("idBut", ""+e.getIdBut()));
        pairs.add(new BasicNameValuePair("idPasse", ""+e.getIdPasse()));
        pairs.add(new BasicNameValuePair("idPartie", "" + e.getIdPartie()));
        pairs.add(new BasicNameValuePair("idPenalite", ""+e.getIdPenalite()));
        pairs.add(new BasicNameValuePair("idLancer", ""+e.getIdLancer()));
        sendRequest(pairs, DbAction.DB_WRITE);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if (status.equalsIgnoreCase("Success")) {
                    newIdx = parser.getIndex();
                }
            }
        }
        return newIdx;
    }

    public Equipe getEquipeInfo(int idEquipe){
        Equipe equipe = null;
        int iEqu = -1, iLi = -1, iSLi = -1;
        String nom = "";
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "getEquipeInfo"));
        pairs.add(new BasicNameValuePair("ID_Equipe", "" + idEquipe));
        sendRequest(pairs, DbAction.DB_READ);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (!status.isEmpty()) {
                if(status.equalsIgnoreCase("Success")) {
                    JSONObject jsonObject = parser.getJSONObject("Equipe");
                    try{
                        iEqu = jsonObject.getInt("Id");
                        iLi = jsonObject.getInt("ligue");
                        iSLi = jsonObject.getInt("sousLigue");
                        nom = jsonObject.getString("nom");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(iEqu == idEquipe)
                        equipe = new Equipe(iEqu, iLi, iSLi, nom);
                }
            }
        }
        return equipe;
    }
}
