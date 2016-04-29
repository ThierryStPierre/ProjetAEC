package com.example.thierry.projetaec.Interfaces;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Fragment_team;
import com.example.thierry.projetaec.Objets.Evenement;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.R;

import java.util.ArrayList;

public class Team1VsTeam2 extends AppCompatActivity implements View.OnClickListener {

    /**
     * Created by daniel on 16-04-15.
     */
    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private DataBaseFront dbFront;
    private ArrayList<Joueur> listJoueurs;
    private ArrayList<Joueur> listJoueurs2;
    private Button btnBut;
    private ArrayList<Button> btn = new ArrayList<>();

    private static int [] eventStore = new int[3];
    private static final int [] btnList = {R.id.btnBut, R.id.btnPasse, R.id.btnPenalite, R.id.btnSave2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team1_vs_team2);
        dbFront = new DataBaseFront(this);//CONNECTION
        setListEquipeFromDb(getIdEquipe());

        btnBut = (Button) findViewById(R.id.btnBut);
        for (int j = 0; j < (btnList.length); j++) {
            btn.add((Button) findViewById(btnList[j]));
            btn.get(j).setOnClickListener(this);
            btn.get(j).setOnDragListener();
        }
        System.out.print("Team1VsTeam2 btnBut = "+ btnBut + "\n\n");
        System.out.flush();
        btnBut.setOnTouchListener(new MyTouchListener());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(2);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    //Permet de changer d'une équipe à une autre en appuyant sur le bouton back
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    //Chercher dans la base de données les informations des joueurs
    public void setListEquipeFromDb(ArrayList<String> idEquipe) {
        //equipe1 = dbFront.getEquipeInfo(Integer.parseInt(idEquipe.get(0)));
        //equipe2 = dbFront.getEquipeInfo(Integer.parseInt(idEquipe.get(1)));
        listJoueurs = dbFront.getListJoueursParEquipe(Integer.parseInt(idEquipe.get(0)));
        listJoueurs2 = dbFront.getListJoueursParEquipe(Integer.parseInt(idEquipe.get(1)));


    }

    static int btnClick = 0;
    public static void addClick() {
        if(++btnClick == 2)
            btn.get(3).setVisible();
    }

    public static void setEventAction(Joueur joueur, View v){
        int idx=0;
        for(idx =0; idx<3; idx++){
            if(btnList[idx] == v.getId())
                break;
        }
        if(idx<3){
            eventStore[idx] = joueur.getIdJoueur();
        }

    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
             if (event.getAction() == MotionEvent.ACTION_DOWN) {
                 ClipData data = ClipData.newPlainText("","");
                 View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
                 v.startDrag(data, sb, v, 0);
        }
            return true;
    }
}
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        LayoutInflater inflater = (LayoutInflater)this.getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        //View sampleActionView = inflater.inflate(R.layout.test, null);

        //MenuItem searchMenuItem = menu.findItem(R.id.action_test2);
        //searchMenuItem.setActionView(sampleActionView);
/*btnBut.setOnDragListener(new View.OnDragListener(){

    Drawable enterShape = getResources().getDrawable(R.drawable.boutton_joueur);
    Drawable normalShape = getResources().getDrawable(R.drawable.boutton_joueur_selectionne);
    public boolean onDrag(View v, DragEvent event){
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(normalShape);
            default:
                break;
        }
        return true;
    }

});
*/
        return super.onCreateOptionsMenu(menu);
    }
            

    //Charger les joueurs de chaque equipe
    public void onClick(View view) {

        if(view.getId() == R.id.btnSave2){
            Evenement e = new Evenement(eventStore[0], eventStore[1], -1, eventStore[2], -1);
            for(int i=0; i< eventStore.length; i++)
                eventStore[i] =-1;
        }
        Snackbar.make(view, "dans le team with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
    //Chercher les id des équipes choisies de l'interface ListEquipe
    public ArrayList<String> getIdEquipe(){
        //  int idLigue = i.getIntExtra("ID_LIGUE",id_Ligue);
        Intent i = new Intent();
        Bundle b = getIntent().getExtras();
        ArrayList<String> idEquipe = b.getStringArrayList("ID_EQUIPE");
        return idEquipe;
    }
    //Charger les deux vues pour la partie
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
       // Insert les 2 fragments dans cette vue
        public Fragment getItem(int position) {
            Fragment team = null;
            Bundle bundle = new Bundle();
            if (position == 0) {
                bundle.putParcelableArrayList("myList", listJoueurs);
                //bundle.putParcelableArrayList("myTeam", equipe1);
            }
            else {
                bundle.putParcelableArrayList("myList", listJoueurs2);
                //bundle.putParcelableArrayList("myTeam", equipe2);
            }

            Fragment fragment = new Fragment_team();
            team = new Fragment_team();
            team.setArguments(bundle);
            return team;
        }

        @Override public int getCount() {
            return NUM_PAGES;
        }

    }
}
