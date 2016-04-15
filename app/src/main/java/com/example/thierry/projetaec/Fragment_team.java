package com.example.thierry.projetaec;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by daniel on 16-04-15.
 */
public class Fragment_team extends Fragment implements View.OnClickListener{
    private ArrayList<Button> btn = new ArrayList<>();
    private int tableBtn[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19,
            R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29,
            R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35, R.id.btn36};
    public Button btnSave;
View view;
        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view =  inflater.inflate(R.layout.content_selection_joueurs, container, false);
            int i = 2;
        btnSave = (Button) view.findViewById(R.id.btnsave);
        for (int j = 0; j < i; j ++){
            btn.add((Button)view.findViewById(tableBtn[j]));
            btn.get(j).setVisibility(View.VISIBLE);
            btn.get(j).setText(("4"));
        }
            return view;
        }

    @Override
    public void onClick(View v) {

        Snackbar.make(view, "toooooown action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
