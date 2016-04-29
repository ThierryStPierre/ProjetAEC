package com.example.thierry.projetaec;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thierry.projetaec.Objets.Joueur;

import java.util.ArrayList;

/**
 * Created by daniel on 16-04-15.
 */
public class Fragment_team extends Fragment implements View.OnClickListener, View.OnDragListener{
    private ArrayList<CheckBox> btn = new ArrayList<>();
    private int tableBtn[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19,
            R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29,
            R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35, R.id.btn36};
    private Button btnSave;
    private int howManyPlayer;
    private TextView txtIdEquipe;
    boolean saveButtonIsPresse=false;
    Drawable normalShape;
    Drawable enterShape;


View view;

public void Fragment_team(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.content_selection_joueurs, container, false);
        btnSave = (Button) view.findViewById(R.id.btnsave);
        btnSave.setOnClickListener(this);
        Bundle bndl = getArguments();
        ArrayList<Joueur> listeJoueurs = bndl.getParcelableArrayList("myList");
        normalShape = getResources().getDrawable(R.drawable.boutton_joueur);
        enterShape = getResources().getDrawable(R.drawable.boutton_joueur_selectionne);
        System.out.print("Fragment_team  listJoueur = " + listeJoueurs + "\n\n");
        howManyPlayer = listeJoueurs.size();
        txtIdEquipe = (TextView) view.findViewById(R.id.idEquipe);
        txtIdEquipe.setText("");
        System.out.flush();
        for (int j = 0; j < howManyPlayer; j++) {
            btn.add((CheckBox) view.findViewById(tableBtn[j]));
            btn.get(j).setVisibility(View.VISIBLE);
            btn.get(j).setText("" + listeJoueurs.get(j).getNumeroChandail());
            btn.get(j).setOnClickListener(this);
            btn.get(j).setOnDragListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View v) {
        CheckBox checkbox = (CheckBox) v;
        if (checkbox == btnSave) {

            for (int j = 0; j < howManyPlayer; j++) {
                if (btn.get(j).isChecked()) {
                    btn.get(j).setChecked(false);
                    btn.get(j).setBackground(getResources().getDrawable(R.drawable.boutton_joueur));
                } else {
                    btn.get(j).setVisibility(View.GONE);

                }
                saveButtonIsPresse = true;
                btnSave.setVisibility(View.GONE);
                v.setBackgroundDrawable(normalShape);
            }
        } else if (saveButtonIsPresse == false) {
            if (checkbox.isChecked()) {
                // Snackbar.make(view, "Joueur choisie" + v.get(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
            } else {
                v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur));
            }

        }
    }



    @Override
    public boolean onDrag(View v, DragEvent event){

        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup

                System.out.print("Fragment_team onDrag() View v = " + v + "\n\n");
                System.out.flush();
                View view = (View) event.getLocalState();
                System.out.print("Fragment_team onDrag() View view = " + view + "\n\n");
                System.out.flush();
                ((Button)view).setText(((Button) v).getText().toString());
                //v.setBackground(getResources().getDrawable(R.drawable.boutton_joueur_selectionne));
//                ViewGroup owner = (ViewGroup) view.getParent();
//                owner.removeView(view);
//                LinearLayout container = (LinearLayout) v;
//                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(normalShape);
            default:
                break;
        }
        return true;
    }
}
