package com.example.thierry.projetaec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thierry.projetaec.Adapteurs.CustomAdapterEquipe;
import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Dialog.Login;
import com.example.thierry.projetaec.Interfaces.Team1VsTeam2;
import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.Objets.LoginObject;

import java.util.ArrayList;
import java.util.List;

public class ListEquipe extends AppCompatActivity {
    private ListView listViewEquipe;
    private Button btnConfirm;
    private CheckedTextView checkBox;
    private TextView txtTeam1;
    private TextView txtTeam2;

    private DataBaseFront dbFront;

    private ArrayList<String> selectedItems;
    private ArrayList<String> checkedItems;
    private List<Equipe> listEquipe;
    private ListAdapter adapter;
    private int idLogin;
    private int i;


    private static int id_Ligue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipe);

        selectedItems = new ArrayList<>();
        checkedItems = new ArrayList<>();
        listEquipe = new ArrayList<>();
        txtTeam1 = (TextView)findViewById(R.id.textTeamt1);
        txtTeam2 = (TextView)findViewById(R.id.textTeamt2);

        dbFront = new DataBaseFront(this);//CONNECTION

        listViewEquipe = (ListView)findViewById(R.id.listEquipe);
        listViewEquipe.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        setListEquipeFromDb(getIdLigue()); //Injection de l'ID Ligue   <-----

        listViewEquipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*int selectedId = (Integer.parseInt(((TextView)
                        view.findViewById(R.id.txtIdEquipe)).getText().toString()));*/

                String selectedId = ((TextView) view.findViewById(R.id.txtIdEquipe))
                        .getText().toString();

                CheckedTextView check = ((CheckedTextView) view.findViewById(R.id.txtNomEquipe));

                String checkedId = String.valueOf(position);

                if (selectedItems.contains(selectedId)) {  //un-check
                    selectedItems.remove(selectedId);
                    checkedItems.remove(String.valueOf(position));

                        if(txtTeam1.getText().toString().equals(check.getText().toString())){
                            txtTeam1.setText("");
                        }
                        else if(txtTeam2.getText().toString().equals(check.getText().toString())){
                            txtTeam2.setText("");
                        }

                    check.toggle();

                } else if (checkedItems.size() < 2) { //check
                    selectedItems.add(selectedId);
                    checkedItems.add(checkedId);
                    noMoreThan2(selectedItems);
                    // noMoreThan2(checkedItems);

                    if (txtTeam1.getText().toString().equals("")){
                        txtTeam1.setText(check.getText().toString());
                    }
                    else if(txtTeam2.getText().toString().equals("")) {
                        txtTeam2.setText(check.getText().toString());
                    }

                    check.toggle();
                    
                } else if (checkedItems.size() > 2) {

                }
            }
        });

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedItems.size() == 2) {
                    Bundle b = new Bundle();
                    Intent i = new Intent(ListEquipe.this, Team1VsTeam2.class);

                    //i.putExtra("LOGIN", getIdLogin());
                    i.putExtra("ID_EQUIPE", selectedItems);

                    startActivity(i);

                    //Toast.makeText(ListEquipe.this, selectedItems + "", Toast.LENGTH_SHORT).show();
                }
                else;

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
      //  int idLigue = i.getIntExtra("ID_LIGUE",id_Ligue);
        Bundle b = getIntent().getExtras();
        int idLigue = b.getInt("ID_LIGUE", -1);
        return idLigue;
    }
    public int getIdLogin(){
        Intent i = new Intent();
        Bundle b = getIntent().getExtras();
        int idLigue = b.getInt("LOGIN", -1);
        return idLigue;
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



    public LoginObject getUser(){
        Intent i = new Intent();
        Bundle b = getIntent().getExtras();
        LoginObject user = b.getParcelable("LOGIN");
        return user;
    }

}
