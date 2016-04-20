package com.example.thierry.projetaec;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Dialog.Login;
import com.example.thierry.projetaec.Objets.Competence;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
// Main Activity  -- ceci est un commentaire pour valider l'échange GitHub 
private Button bouttonPartie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Raaaaedfsdfplace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        bouttonPartie  = (Button) findViewById(R.id.btnPartie);
        bouttonPartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  login();
               Intent i = new Intent(MainActivity.this, ListeLigue.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.pop_formulaire_login);
        dialog.setTitle("Login");
        final EditText txtLoginUserName = (EditText)dialog.findViewById(R.id.txtDialogUsername);
        final EditText txtLoginPassword = (EditText)dialog.findViewById(R.id.txtDialogPassword);
        Button btnDialogLogin = (Button)dialog.findViewById(R.id.btnDialogLogin);
        Button btnDialogCancel = (Button)dialog.findViewById(R.id.btnDialogAnnuler);
        dialog.show();

        btnDialogLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtLoginUserName.getText().toString();
                String password = txtLoginPassword.getText().toString();

                if (1 == 1) {//Vérification du mot de pass
                    Intent i = new Intent(MainActivity.this, ListeLigue.class);
                    int idGestionnaire = 1;//dummy data
                    i.putExtra("LOGIN", idGestionnaire);
                    startActivity(i);

                } else {//Échec a la vérification

                }
            }
        });
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public boolean verifyInfo(String user, String pass){//INCOMPLETE
        boolean boo = false;
        DataBaseFront dbFront = new DataBaseFront(this);
        List<Competence> c = dbFront.validateLogin(user, pass);
        return boo;
    }


}
