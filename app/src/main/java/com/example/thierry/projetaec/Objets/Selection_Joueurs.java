package com.example.thierry.projetaec.Objets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.thierry.projetaec.R;

import java.util.ArrayList;

public class Selection_Joueurs extends AppCompatActivity {

    private ArrayList<Button> btn = new ArrayList<>();
    private int tableBtn[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19,
            R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29,
            R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35, R.id.btn36};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_joueurs);
        int i = 2;
        for (int j = 0; j < i; j ++){
            btn.add((Button)findViewById(tableBtn[j]));
            btn.get(j).setVisibility(View.VISIBLE);
            btn.get(j).setText(("4"));
        }
    }
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()) {
                    v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
                }
                else
                    v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur));

    }

}
