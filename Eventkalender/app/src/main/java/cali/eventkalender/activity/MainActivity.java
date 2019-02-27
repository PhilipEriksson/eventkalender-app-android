package cali.eventkalender.activity;

import android.app.ActionBar;
import android.app.Application;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import cali.eventkalender.R;
import cali.eventkalender.fragment.EventsListFragment;
import cali.eventkalender.fragment.NationListFragment;
import cali.eventkalender.fragment.ProfileFragment;
import cali.eventkalender.fragment.SearchFragment;
import cali.eventkalender.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private EventsListFragment mEventsListFragment;
    private NationListFragment mNationListFragment;
    private SearchFragment mSearchFragment;
    private ProfileFragment mProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEventsListFragment = new EventsListFragment();
        mNationListFragment = new NationListFragment();
        mSearchFragment = new SearchFragment();
        mProfileFragment = new ProfileFragment();

        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.action_bar_items,
                null);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
        }
        final int actionBarColor = getResources().getColor(R.color.eventkalenderBlue);
        actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

        final Button actionBarCityIcon = (Button) findViewById(R.id.action_bar_city);
        /** Hade använts om det dynamisk ska ändras för städer, satt permanent i xml nu, framtidskomponent*/
        //actionBarCityIcon.setBackgroundResource(R.mipmap.baseline_location_city_white_48dp);

        final Button actionBarCityName = (Button) findViewById(R.id.action_bar_city_icon);
        actionBarCityName.setText("Lund");


        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        setFragment(mEventsListFragment);
      //  setStartFragment(mEventsListFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_event :
                        setFragment(mEventsListFragment);
                        return true;

                    case R.id.nav_nation :
                        setFragment(mNationListFragment);
                        return true;

                    case R.id.nav_search :
                        setFragment(mSearchFragment);
                        return true;

                    case R.id.nav_profile :
                        setFragment(mProfileFragment);
                        return true;

                    default :
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){

  /**      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
   */
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.main_frame, fragment).commit();
    }


}
