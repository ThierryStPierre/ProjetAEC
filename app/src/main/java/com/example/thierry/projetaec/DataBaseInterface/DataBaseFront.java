package com.example.thierry.projetaec.DataBaseInterface;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
import java.lang.Override;import java.lang.Runnable;import java.lang.String;import java.lang.System;import java.lang.Thread;import java.util.ArrayList;
import java.util.List;

/**
 * Created by 201596474 on 2016-03-11.
 */
/*
private class PostTask extends AsyncTask<String, String, String> {
    public static final String strURL = "http://192.168.140.105/Labo3/gestionContacts.php";
    @Override
    protected String doInBackground(String... data){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(strURL);
    }
}
*/


public class DataBaseFront {

    static SQLiteDatabase db = null;
    protected Cursor cursor; //voir diapositive suivant
//    public static final String strURL = "http://10.0.2.2/ProjetAEC/connect_ligue.php";
    public static final String strURL = "http://http://thierrystpierre.ddns.net:81/ProjetAEC/connect_ligue.php";
    private List<NameValuePair> paramRequete;
    private JSONParser parser;
    private String ligneResult = null;
    public volatile boolean parsingComplete = true;

    public DataBaseFront(Context cntx) {
    }

    public String getLigneResult(){
        return ligneResult;
    }

    public void clearParsingComplete(){
        parsingComplete = true;
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
                    HttpPost httppost = new HttpPost(strURL);
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
                    HttpPost httppost = new HttpPost(strURL);
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

    public List<Joueur> getListJoueurs(int idEquipe, int idSaison) {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeJoueur"));
        pairs.add(new BasicNameValuePair("idEquipe", ""+idEquipe));
//        pairs.add(new BasicNameValuePair("idSaison", ""+idSaison));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            parser =  new JSONParser(ligneResult);
            JSONArray joueurArray = parser.getList("Joueur");
            if(joueurArray != null){
                liste = new ArrayList<Joueur>();
                for(int index = 0; index < joueurArray.length(); index++){
                    try {
                        JSONObject jsonObject = joueurArray.getJSONObject(index);
                        Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                                        jsonObject.getString("nom"),
                                                        jsonObject.getString("prenom"));
                        liste.add(joueur);
                    } catch (JSONException e) {
                        e.printStackTrace();
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
        JSONArray ligueArray = parser.getList("Ligues");
        System.out.print("DataBaseFront.getListLigues() ligueArray  = " + ligueArray + "\n\n");
        System.out.flush();
        if(ligueArray != null){
            liste = new ArrayList<Ligue>();
            for(int index = 0; index < ligueArray.length(); index++){
                try {
                    JSONObject jsonObject = ligueArray.getJSONObject(index);
                    System.out.print("DataBaseFront.getListLigues() jsonObject["+index+"] = " + jsonObject + "\n\n");
                    System.out.flush();
                    Ligue ligue = new Ligue(jsonObject.getInt("id"),
                                                    jsonObject.getString("nom")/*,
                                                    jsonObject.getInt("idOwnder")*/);
                    liste.add(ligue);
                } catch (JSONException e) {
                    e.printStackTrace();
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
        if(ligueArray != null){
            liste = new ArrayList<Equipe>();
            for(int index = 0; index < ligueArray.length(); index++){
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
        return liste;
    }

    public List<Joueur> getListGestionnaires() {
        ArrayList<Joueur> liste = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "listeGestionaires"));
        parsingComplete = false;
        sendRequest(pairs);
        while(parsingComplete == false);

        System.out.print("DataBaseFront.getListEquipes() ligneResult = " + ligneResult + "\n\n");
        System.out.flush();

        if(ligneResult != null) {
            parser =  new JSONParser(ligneResult);
            JSONArray joueurArray = parser.getList("Gestionnaires");
            if(joueurArray != null){
                liste = new ArrayList<Joueur>();
                for(int index = 0; index < joueurArray.length(); index++){
                    try {
                        JSONObject jsonObject = joueurArray.getJSONObject(index);
                        Joueur joueur = new Joueur(jsonObject.getInt("id"),
                                                    jsonObject.getString("nom"),
                                                    jsonObject.getString("prenom"));
                        System.out.print("DataBaseFront.getListEquipes() joueur["+index+"] = " + joueur + "\n\n");
                        System.out.flush();
                        liste.add(joueur);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return liste;
    }


/*
    public Membre getMemberById(int id){
        Membre membre = null;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "detail"));
        pairs.add(new BasicNameValuePair("idContact", "" + id));
        sendRequest(pairs);
        while(parsingComplete == false);

        if(ligneResult != null) {
            Document doc =parser.getDocument(ligneResult);
            NodeList nodeList = doc.getElementsByTagName(NODE_CONTACT);

            if (nodeList != null) {
                Element e = (Element) nodeList.item(0);
                membre = new Membre(
                        id,
                        parser.getValue(e, NODE_NOM),
                        parser.getValue(e, NODE_PRENOM),
                        parser.getValue(e, NODE_SEXE),
                        Integer.parseInt(parser.getValue(e, NODE_AGE)),
                        parser.getValue(e, NODE_TEL),
                        parser.getValue(e, NODE_COURRIEL));
            }
        }
        return membre;
    }

    public boolean EffaceMembre(int id){
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "effacer"));
        pairs.add(new BasicNameValuePair("idContact", ""+id));
        sendRequestNoResponse(pairs);
        while(parsingComplete == false); // attendre la fin de la transaction avant de retourner à la liste
        return true;
    }

    void ecrireMembre2DB(Membre m){

        String[] params = Membre.getArrayFields();
        String[] values = m.getArrayFieldsContent();
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "enregistrer"));
        // || is the concatenation operation in SQLite
        int i;
        for(i=1; i<params.length ; i++)
            pairs.add(new BasicNameValuePair(params[i], values[i]));
        sendRequestNoResponse(pairs);

    }

    void updateMembre(Membre m){
        String[] params = Membre.getArrayFields();
        String[] values = m.getArrayFieldsContent();
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("action", "update"));
        // || is the concatenation operation in SQLite
        int i;
        for(i=0; i<params.length ; i++)
            pairs.add(new BasicNameValuePair( params[i], values[i]));
        sendRequestNoResponse(pairs);
    }*/
}
