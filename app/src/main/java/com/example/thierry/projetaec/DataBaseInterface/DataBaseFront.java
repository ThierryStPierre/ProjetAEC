package com.example.thierry.projetaec.DataBaseInterface;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.thierry.projetaec.Objets.Competence;
import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.Evenement;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.Objets.LoginObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 201596474 on 2016-03-11.
 */

public class DataBaseFront extends DbAccess {
    DbAccess dba = null;

    private Context parentContext = null;
    private boolean workLocaly = true;
    private boolean parsingComplete = false;
    private String ligneResult = null;

    public DataBaseFront(Context cntx) {
        parentContext = cntx;
        if(isOnline()){
            if(testConenctionToServer())
                workLocaly = false;
        }

        if(workLocaly)
            dba = new DbAccessSqlite(parentContext);
        else
            dba = new DbAccessRemote(parentContext);
    }

    private boolean isOnline()
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager) parentContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private boolean testConenctionToServer(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://thierrystpierre.ddns.net:81/ProjetAEC/index.php");
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
        while(parsingComplete == false);
        return (ligneResult != null);
    }


    @Override
    public List<Joueur> getListJoueurs() {
        return dba.getListJoueurs();
    }

    @Override
    public List<Joueur> getListJoueursParEquipe(int idEquipe/*, int idSaison*/) {
        return dba.getListJoueursParEquipe(idEquipe/*, idSaison*/);
    }

    @Override
    public List<Joueur> getListJoueursParLigue(int idLigue) {
        return dba.getListJoueursParLigue(idLigue);
    }

    @Override
    public List<Ligue> getListLigues(int idGestionnaire) {
        return dba.getListLigues(idGestionnaire);
    }

    @Override
    public List<Equipe> getListEquipes(int idLigue) {
        return dba.getListEquipes(idLigue);
    }

    @Override
    public List<Joueur> getListGestionnaires() {
        return dba.getListGestionnaires();
    }

    @Override
    public LoginObject validateLogin(String user, String pass) {
        return dba.validateLogin(user, pass);
    }

    @Override
    public List<Ligue> getListAccreditedLigues(int idMarqueur){
        return dba.getListAccreditedLigues(idMarqueur);
    }

    public int ecritEvenement(Evenement e){
        return dba.ecritEvenement(e);
    }
}
