package com.example.thierry.projetaec;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.thierry.projetaec.Interfaces.Team1VsTeam2;

import java.util.ArrayList;

/**
 * Created by daniel on 16-04-15.
 */
public class Fragment_team2 extends Fragment implements View.OnClickListener{
    private ArrayList<Button> btn = new ArrayList<>();
    private int tableBtn[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19,
            R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29,
            R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35, R.id.btn36};
    private Button btnSave;
    private CheckBox chkb;
View view;
        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view =  inflater.inflate(R.layout.content_selection_joueurs, container, false);
            int i = 20;
        chkb =  (CheckBox) view.findViewById(R.id.btn1);
            btnSave = (Button) view.findViewById(R.id.btnsave);
            btnSave.setOnClickListener(this);
            //Snackbar.make(view, "Ca va?", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            for (int j = 0; j < i; j++) {
                btn.add((Button)view.findViewById(tableBtn[j]));
                btn.get(j).setVisibility(View.VISIBLE);
                btn.get(j).setText(("2"));
                btn.get(j).setBackgroundColor(0xFF00FF00);
                btn.get(j).setOnClickListener(this);
        }
            return view;
        }

    @Override
    public void onClick(View v) {
        CheckBox checkbox = (CheckBox)v;
        if(checkbox == btnSave){
            /*Bundle b = new Bundle();
            Intent i = new Intent(Selection_Joueurs.this, Team1VsTeam2.class);
            Toast.makeText(Selection_Joueurs.this, "Sauvegarde terminé", Toast.LENGTH_SHORT).show();
            startActivity(i);*/
        }
        else if(checkbox.isChecked()){
            v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
        }
        else
            v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur));

    }
}
