package com.example.thierry.projetaec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thierry.projetaec.Adapteurs.CustomAdapterLigue;
import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.Objets.LoginObject;

import java.util.ArrayList;
import java.util.List;

public class ListeLigue extends AppCompatActivity {
    private ListView listViewLigue;
    private DataBaseFront dbFront;
    private int selectedId;
    private List<Ligue> listLigue;
    private LoginObject currentUser;

    private static final int ID_GESTIONNAIRE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_ligue);


        listLigue = new ArrayList<>();

        dbFront = new DataBaseFront(this);//CONNECTION A LA DB

        listViewLigue = (ListView) findViewById(R.id.listViewLigue);
        currentUser = getUser();
        System.out.print( "\n\n\n\n\n\n\n\n--------------------------------------------------"+currentUser.getPrenom());

        setListLigueFromDb(1);  // Injection de l'ID GESTIONNAIRE <------==
        System.out.println(currentUser + "\n\n\n");

        listViewLigue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedId = Integer.parseInt(
                        ((TextView) view.findViewById(R.id.txtIdLigue)).getText().toString());
                // Toast.makeText(ListeLigue.this,selectedId+ "", Toast.LENGTH_SHORT).show();
                sendId(selectedId);
            }
        });
    }

    public void setListLigueFromDb(int idGestionnaire) {
        listLigue = dbFront.getListLigues(idGestionnaire);
        // listViewLigue.setAdapter(new CustomAdapterLigue(this, dummyList()));
        listViewLigue.setAdapter(new CustomAdapterLigue(this, listLigue));
    }

    public List<Ligue> dummyList() {
        List<Ligue> dummy = new ArrayList<>();
        dummy.add(new Ligue(1, "dummy team 1"));
        dummy.add(new Ligue(2, "dummy team 2"));
        dummy.add(new Ligue(3, "dummy team 3"));
        dummy.add(new Ligue(4, "dummy team 4"));
        dummy.add(new Ligue(5, "dummy team 5"));
        dummy.add(new Ligue(6, "dummy team 6"));
        dummy.add(new Ligue(7, "dummy team 7"));
        dummy.add(new Ligue(8, "dummy team 8"));
        dummy.add(new Ligue(9, "dummy team 9"));
        dummy.add(new Ligue(10, "dummy team 10"));
        dummy.add(new Ligue(11, "dummy team 11"));
        dummy.add(new Ligue(12, "dummy team 12"));
        dummy.add(new Ligue(13, "dummy team 13"));
        dummy.add(new Ligue(14, "dummy team 14"));
        dummy.add(new Ligue(15, "dummy team 15"));
        dummy.add(new Ligue(16, "dummy team 16"));

        return dummy;
    }

    public void sendId(int idLigue) {
        Intent i = new Intent(ListeLigue.this, ListEquipe.class);
        i.putExtra("ID_LIGUE", idLigue);
        i.putExtra("LOGIN", currentUser);
        startActivity(i);
    }

    /*public int getIdGestionnaire() {
        Intent i = new Intent();
        Bundle b = getIntent().getExtras();
        int id = b.getInt("LOGIN", 1);
        return id;
    }*/

    public LoginObject getUser() {
        Intent i = new Intent();
        Bundle b = getIntent().getExtras();
        LoginObject user = b.getParcelable("LOGIN");
        return user;
    }
}
