package cali.eventkalender.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.fragment.NationFragment;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;

public class NationActivity extends AppCompatActivity {

    public static final String EXTRA_NATION_ID = "cali.eventkalender.nation_id";
    private static final String SAVED_NATION = "SAVED_NATION";

    private Nation mNation;
    private UUID mNationId;

    public static Intent newIntent(Context packageContext, UUID nationId) {
        Intent intent = new Intent(packageContext, NationActivity.class);
        intent.putExtra(EXTRA_NATION_ID, nationId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);

        if(savedInstanceState != null) {
            mNation = savedInstanceState.getParcelable(SAVED_NATION);
            for(Event event: mNation.getEvents()) {
                event.setNation(mNation);
            }

        }
        else {
             mNationId = (UUID) getIntent().getSerializableExtra(EXTRA_NATION_ID);
             mNation = NationLab.get(getApplicationContext()).getNation(mNationId);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

      //  UUID nationId = (UUID) getIntent().getSerializableExtra(EXTRA_NATION_ID);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = NationFragment.newInstance(mNation.getId());
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_NATION, mNation);
    }


}
