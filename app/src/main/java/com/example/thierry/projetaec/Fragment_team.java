package com.example.thierry.projetaec;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.thierry.projetaec.Objets.Joueur;

import java.util.ArrayList;

/**
 * Created by daniel on 16-04-15.
 */
public class Fragment_team extends Fragment implements View.OnClickListener{
    private ArrayList<CheckBox> btn = new ArrayList<>();
    private int tableBtn[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19,
            R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29,
            R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35, R.id.btn36};
    private Button btnSave;
    private Button btnAllNone;
    private int howManyPlayer;
    private boolean allSelect = false;
    private TextView txtIdEquipe;

View view;

public void Fragment_team(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.content_selection_joueurs, container, false);
        btnSave = (Button) view.findViewById(R.id.btnsave);
        btnSave.setOnClickListener(this);
        btnAllNone = (Button) view.findViewById(R.id.btncheckall);
        btnAllNone.setOnClickListener(this);
        Bundle bndl = getArguments();
        ArrayList<Joueur> listeJoueurs = bndl.getParcelableArrayList("myList");

        System.out.print("Fragment_team  listJoueur = " + listeJoueurs + "\n\n");
        howManyPlayer = listeJoueurs.size();
        txtIdEquipe = (TextView) view.findViewById(R.id.idEquipe);
        txtIdEquipe.setText("");
        System.out.flush();
        for (int j = 0; j < howManyPlayer; j++) {
            btn.add((CheckBox) view.findViewById(tableBtn[j]));
            btn.get(j).setVisibility(View.VISIBLE);
            btn.get(j).setText(listeJoueurs.get(j).getNom());
            btn.get(j).setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View v) {

        CheckBox checkbox = (CheckBox)v;
        if(checkbox == btnSave){

            for (int j = 0; j < howManyPlayer; j ++) {
                if (btn.get(j).isChecked()) {

                }
                else{
                    btn.get(j).setVisibility(View.GONE);

                }
            }
        }
        else if(checkbox == btnAllNone){
            if (allSelect == false)
            for (int j = 0; j < howManyPlayer; j ++){
                btn.get(j).setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
                allSelect = true;
            }
            else
                for (int j = 0; j < howManyPlayer; j ++) {
                    btn.get(j).setBackground(getResources().getDrawable(R.drawable.boutton_joueur));
                    allSelect = false;
                }
        }
        else if(checkbox.isChecked()){
           // Snackbar.make(view, "Joueur choisie" + v.get(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
        }
        else
            v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur));

    }

}
