package com.example.thierry.projetaec.DataBaseInterface;

import android.content.Context;
import android.widget.Toast;

import com.example.thierry.projetaec.Objets.Competence;
import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.Objets.Ligue;

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
    private List<NameValuePair> paramRequete;
    private JSONParser parser;
    private String ligneResult = null;
    public volatile boolean parsingComplete = true;

    public DbAccessRemote(Context cntx){

    }

    private void sendRequest(List<NameValuePair> pairs){
        paramRequete = pairs;
        ligneResult = null;
        parsingComplete = false;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(strURLRd);
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

    private void sendRequestNoResponse(List<NameValuePair> pairs){
        paramRequete = pairs;
        ligneResult = null;
        parsingComplete = false;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(strURLRd);
                    httppost.setEntity(new UrlEncodedFormEntity(paramRequete));
                    HttpResponse response = httpclient.execute(httppost);
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
        pairs.add(new BasicNameValuePair("action", "listeJoueur"));
//        pairs.add(new BasicNameValuePair("typeRecherche", "ligue"));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser =  new JSONParser(ligneResult);
            String status = parser.getStatus();
            if(status.equalsIgnoreCase("Success")) {
                JSONArray joueurArray = parser.getList("Alignement");
                if (joueurArray != null) {
                    liste = new ArrayList<Joueur>();
                    for (int index = 0; index < joueurArray.length(); index++) {
                        try {
                            System.out.println("ca rentre ou pas dans dbaccessRemote");
                            JSONObject jsonObject = joueurArray.getJSONObject(index);
                            Joueur joueur = new Joueur(jsonObject.getInt("id"),
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

        return liste;
    }

    public List<Joueur> getListJoueursParLigue(int idLigue) {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeJoueurLigue"));
        pairs.add(new BasicNameValuePair("idLigue", "" + idLigue));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser =  new JSONParser(ligneResult);
            String status = parser.getStatus();
            if(status.equalsIgnoreCase("Success")) {
                JSONArray joueurArray = parser.getList("Alignement");
                if (joueurArray != null) {
                    liste = new ArrayList<Joueur>();
                    for (int index = 0; index < joueurArray.length(); index++) {
                        try {
                            JSONObject jsonObject = joueurArray.getJSONObject(index);
                            Joueur joueur = new Joueur(jsonObject.getInt("id"),
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

        return liste;
    }

    public List<Joueur> getListJoueursParEquipe(int idEquipe, int idSaison) {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeJoueur"));
        pairs.add(new BasicNameValuePair("idEquipe", ""+idEquipe));
        pairs.add(new BasicNameValuePair("idSaison", "" + idSaison));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser =  new JSONParser(ligneResult);
            String status = parser.getStatus();
            if(status.equalsIgnoreCase("Success")) {
                JSONArray joueurArray = parser.getList("Alignement");
                if (joueurArray != null) {
                    liste = new ArrayList<Joueur>();
                    for (int index = 0; index < joueurArray.length(); index++) {
                        try {
                            JSONObject jsonObject = joueurArray.getJSONObject(index);
                            Joueur joueur = new Joueur(jsonObject.getInt("id"),
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

        return liste;
    }

    public List<Ligue> getListLigues(int idGestionnaire) {
        ArrayList<Ligue> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeLigue"));
        if(idGestionnaire > 0)
            pairs.add(new BasicNameValuePair("idGestionnaire", "" + idGestionnaire));
        sendRequest(pairs);
        while(parsingComplete == false);

        parser =  new JSONParser(ligneResult);
        String status = parser.getStatus();
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
        return liste;
    }

    public List<Equipe> getListEquipes(int idLigue) {
        ArrayList<Equipe> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeEquipe"));
        pairs.add(new BasicNameValuePair("idLigue", "" + idLigue));
        sendRequest(pairs);
        while(parsingComplete == false);

        parser =  new JSONParser(ligneResult);
        JSONArray ligueArray = parser.getList("Equipes");
        String status = parser.getStatus();
        if(status.equalsIgnoreCase("Success")) {
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
        return liste;
    }

    public List<Joueur> getListGestionnaires() {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeGestionnaires"));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser = new JSONParser(ligneResult);
            String status = parser.getStatus();
            if (status.equalsIgnoreCase("Success")) {
                JSONArray joueurArray = parser.getList("Gestionnaires");
                if (joueurArray != null) {
                    liste = new ArrayList<Joueur>();
                    for (int index = 0; index < joueurArray.length(); index++) {
                        try {
                            JSONObject jsonObject = joueurArray.getJSONObject(index);
                            Joueur joueur = new Joueur(jsonObject.getInt("id"),
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

        return liste;
    }

    public List<Ligue> getListAccreditedLigues(int idMarqueur){
        ArrayList<Ligue> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeLigueParMarqueur"));
        pairs.add(new BasicNameValuePair("idMarqueur", "" + idMarqueur));
        sendRequest(pairs);
        while(parsingComplete == false);

        parser =  new JSONParser(ligneResult);
        String status = parser.getStatus();
        if(status.equalsIgnoreCase("Success")) {
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

        return liste;
    }

    public List<Competence> validateLogin(String user, String pass){
        ArrayList<Competence> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeLigueParMarqueur"));
        pairs.add(new BasicNameValuePair("userName", user));
        pairs.add(new BasicNameValuePair("passWord", pass));
        sendRequest(pairs);
        while(parsingComplete == false);

        parser =  new JSONParser(ligneResult);
        String status = parser.getStatus();
        if(status.equalsIgnoreCase("Success")) {
            JSONArray ligueArray = parser.getList("Ligues");
            if (ligueArray != null) {
                liste = new ArrayList<Competence>();
                for (int index = 0; index < ligueArray.length(); index++) {
                    try {
                        JSONObject jsonObject = ligueArray.getJSONObject(index);
                        String comp = jsonObject.getString("competenceValue");
                        String sousLigue = jsonObject.getString("sous-ligue");
                        String equipe = jsonObject.getString("equipe");
                        int idLigue=jsonObject.getInt("ligue");
                        int idSousLigue=-1;
                        int idEquipe = -1;
                        Competence.competenceType typeComp = Competence.competenceType.NO_COMPETENCE;
                        switch(comp){
                            case "Gestionnaire":
                                typeComp=Competence.competenceType.GESTIONNAIRE;
                                break;
                            case "Capitaine":
                                typeComp=Competence.competenceType.CAPITAINE;
                                break;
                            case "Marqueur":
                                typeComp=Competence.competenceType.MARQUEUR;
                                break;
                        }
                        if(equipe != "")
                            idEquipe = Integer.parseInt(equipe);
                        if(sousLigue != "")
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

        return liste;
    }

}
