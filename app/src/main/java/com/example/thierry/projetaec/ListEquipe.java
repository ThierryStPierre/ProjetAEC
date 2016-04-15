package com.example.thierry.projetaec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thierry.projetaec.Adapteurs.CustomAdapterEquipe;
import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Interfaces.Team1VsTeam2;
import com.example.thierry.projetaec.Objets.Equipe;

import java.util.ArrayList;
import java.util.List;

public class ListEquipe extends AppCompatActivity {
    private ListView listViewEquipe;
    private Button btnConfirm;
    private CheckedTextView checkBox;

    private DataBaseFront dbFront;

    private ArrayList<String> selectedItems;
    private ArrayList<String> checkedItems;
    private List<Equipe> listEquipe;
    private ListAdapter adapter;

    private static int id_Ligue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipe);

        selectedItems = new ArrayList<>();
        checkedItems = new ArrayList<>();
        listEquipe = new ArrayList<>();



        dbFront = new DataBaseFront(this);//CONNECTION

        listViewEquipe = (ListView)findViewById(R.id.listEquipe);
        listViewEquipe.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        setListEquipeFromDb(getIdLigue());

        listViewEquipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*int selectedId = (Integer.parseInt(((TextView)
                        view.findViewById(R.id.txtIdEquipe)).getText().toString()));*/

                String selectedId = ((TextView)view.findViewById(R.id.txtIdEquipe))
                        .getText().toString();

                CheckedTextView check = ((CheckedTextView)view.findViewById(R.id.txtNomEquipe));

                String checkedId = String.valueOf(position);

                if(selectedItems.contains(selectedId)){  //un-check
                    selectedItems.remove(selectedId);
                    checkedItems.remove(String.valueOf(position));

                }
                else { //check
                    selectedItems.add(selectedId);
                    checkedItems.add(checkedId);
                    noMoreThan2(selectedItems);
                   // noMoreThan2(checkedItems);

                }
                if(checkedItems.size() > 2){
                    check.toggle();
                }
                else
                check.toggle();
            }
        });

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent i = new Intent(ListEquipe.this, Team1VsTeam2.class);
                startActivity(i);

                //Toast.makeText(ListEquipe.this, returnPositionToUncheck(checkedItems)+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListEquipeFromDb(int idLigue){
        listEquipe = dbFront.getListEquipes(idLigue);
        listViewEquipe.setAdapter(new CustomAdapterEquipe(this, listEquipe));
        //listViewEquipe.setAdapter(new CustomAdapterEquipe(this, dummyList()));
    }

    public List<Equipe> dummyList(){
        List<Equipe> dummy = new ArrayList<>();
        dummy.add(new Equipe(1, 1, "dummy team 1"));
        dummy.add(new Equipe(2, 2, "dummy team 2"));
        dummy.add(new Equipe(3, 3, "dummy team 3"));
        dummy.add(new Equipe(4, 4, "dummy team 4"));
        dummy.add(new Equipe(5, 5, "dummy team 5"));
        dummy.add(new Equipe(6, 6, "dummy team 6"));
        dummy.add(new Equipe(7, 7, "dummy team 7"));
        dummy.add(new Equipe(8, 8, "dummy team 8"));
        dummy.add(new Equipe(9, 9, "dummy team 9"));
        dummy.add(new Equipe(10, 10, "dummy team 10"));
        dummy.add(new Equipe(11, 11, "dummy team 11"));
        dummy.add(new Equipe(12, 12, "dummy team 12"));
        dummy.add(new Equipe(13, 13, "dummy team 13"));
        dummy.add(new Equipe(14, 14, "dummy team 14"));
        dummy.add(new Equipe(15, 15, "dummy team 15"));
        dummy.add(new Equipe(16, 16, "dummy team 16"));

        return dummy;
    }

    public int getIdLigue(){
        Intent i = new Intent();
        int idLigue = i.getIntExtra("ID_LIGUE",0);
        return idLigue;
    }

    public void sendId(int id){
        //faire un bundle et envoyé les id
    }

    public void noMoreThan2(ArrayList<String> array){
        if(array.size() > 2){
            String temp = array.get(1);
            array.set(0, temp);
            String temp2 = array.get(2);
            array.set(1, temp2);
            array.remove(2);
        }
    }
    public String returnPositionToUncheck(ArrayList<String> array){
        int temp = 0;
        if(array.size() >= 2){
            temp = array.size() - 3;
            array.get(temp);
        }
        return array.get(temp);
    }
}
