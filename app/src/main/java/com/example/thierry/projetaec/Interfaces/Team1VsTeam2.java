package com.example.thierry.projetaec.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.thierry.projetaec.DataBaseInterface.DataBaseFront;
import com.example.thierry.projetaec.Fragment_team;
import com.example.thierry.projetaec.Fragment_team2;
import com.example.thierry.projetaec.Objets.Joueur;
import com.example.thierry.projetaec.R;

import java.util.ArrayList;
import java.util.List;

public class Team1VsTeam2 extends AppCompatActivity {

    /**
     * Created by daniel on 16-04-15.
     */
    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private DataBaseFront dbFront;
    private List<Joueur> listJoueurs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team1_vs_team2);
            dbFront = new DataBaseFront(this);//CONNECTION
            setListEquipeFromDb(getIdEquipe());
            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setOffscreenPageLimit(2);
            mPagerAdapter = new  ScreenSlidePagerAdapter(getSupportFragmentManager());
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
        int equipe2 = 2;

        listJoueurs = dbFront.getListJoueursParEquipe(equipe2);
        String test2 = listJoueurs.get(0).toString();
        Toast.makeText(Team1VsTeam2.this, test2, Toast.LENGTH_SHORT).show();

    }
    //Charger les joueurs de chaque equipe
    public void onClick(View view) {

        Snackbar.make(view, "dans le team with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
    //Chercher les id des équipes choisies de l'interface ListEquipe
    public ArrayList<String> getIdEquipe(){
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
            if (position == 0) {
                String test2 = listJoueurs.get(0).toString();
                System.out.println(listJoueurs.get(0).toString());
                ArrayList<Joueur> newone = new ArrayList<>(listJoueurs);
                Bundle bundle = new Bundle();
                //bundle.putarr("myList", newone);

                Fragment fragment = new Fragment_team();
                fragment.setArguments(bundle);
                //Toast.makeText(Team1VsTeam2.this, test2, Toast.LENGTH_SHORT).show();
                /*Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString(key, value);
                fragment.setArguments(bundle);*/
                return new Fragment_team();
            }
            else
                return new Fragment_team2();
        }
        @Override public int getCount() {
            return NUM_PAGES;
        }

    }
}
