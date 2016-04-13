package com.example.thierry.projetaec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Objets.Equipe;

import java.util.ArrayList;
import java.util.List;

public class ListEquipe extends AppCompatActivity {
    private ListView listViewEquipe;
    private Button btnConfirm;
    DataBaseFront dbFront;
    ArrayList<String> selectedItems = new ArrayList<>();
    ArrayList<Equipe> listEquipe;
    private static final int ID_LIGUE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipe);

        dbFront = new DataBaseFront(this);

        listViewEquipe = (ListView)findViewById(R.id.listEquipe);
        listViewEquipe.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] items = {"a", "b", "c", "d", "e"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_equipe_layout, R.id.txt_lan,items);

        listViewEquipe.setAdapter(adapter);
        listViewEquipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                selectedItems.add(selectedItem);
            }
        });

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp(v);
            }
        });

    }












    public void getListEquipeFromDb(){
        listEquipe = new ArrayList<>();
        List<Equipe> listEquipe = dbFront.getListEquipes(ID_LIGUE);





    }

















    public void temp(View view){
        String items="";
        for(String item:selectedItems){
            items+=""+item+"\n";
        }
        Toast.makeText(ListEquipe.this, ""+items, Toast.LENGTH_SHORT).show();
    }

}
