package com.example.thierry.projetaec.Interfaces;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.thierry.projetaec.Fragment_team;
import com.example.thierry.projetaec.Fragment_team2;
import com.example.thierry.projetaec.R;

public class Team1VsTeam2 extends AppCompatActivity {

    /**
     * Created by daniel on 16-04-15.
     */
    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team1_vs_team2);
            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setOffscreenPageLimit(2);
            mPagerAdapter = new  ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
       }

    @Override

    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void onClick(View view) {

        Snackbar.make(view, "dans le team with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
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
