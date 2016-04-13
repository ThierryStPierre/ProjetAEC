package com.example.thierry.projetaec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thierry.projetaec.Adapteurs.CustomAdapterEquipe;
import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Objets.Equipe;

import java.util.ArrayList;
import java.util.List;

public class ListEquipe extends AppCompatActivity {
    private ListView listViewEquipe;
    private Button btnConfirm;

    private DataBaseFront dbFront;

    private int[] selectedItems;
    private List<Equipe> listEquipe;
    private ListAdapter adapter;

    private static final int ID_LIGUE = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipe);

        listEquipe = new ArrayList<>();
        dbFront = new DataBaseFront(this);

        listViewEquipe = (ListView)findViewById(R.id.listEquipe);
        listViewEquipe.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        getListEquipeFromDb();

        listViewEquipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Get text of ID
            }
        });

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void getListEquipeFromDb(){

        //listEquipe = dbFront.getListEquipes(ID_LIGUE);
        listViewEquipe.setAdapter(new CustomAdapterEquipe(this, dummyList()));
    }
    public List<Equipe> dummyList(){
        List<Equipe> dummy = new ArrayList<>();
        dummy.add(new Equipe(1, 1, "dummy team 1"));
        dummy.add(new Equipe(2, 2, "dummy team 2"));
        dummy.add(new Equipe(3, 3, "dummy team 3"));
        return dummy;
    }

    public void sendId(){
        //faire un bundle et envoyé les id
    }

}
