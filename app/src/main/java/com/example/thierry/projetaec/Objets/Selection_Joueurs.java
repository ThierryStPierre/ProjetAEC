package com.example.thierry.projetaec.Objets;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.Toolbar;



import com.example.thierry.projetaec.R;

public class Selection_Joueurs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_joueurs);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }
            public void onClick(View v) {

                   // v.setPressed(true);

                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()) {
                    v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
                }
                else
                    v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur));



    }

}
