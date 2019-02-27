package cali.eventkalender.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;
import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.fragment.EventFragment;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;
import cali.eventkalender.utils.CustomViewPager;
import cali.eventkalender.utils.ImageAdapter;

public class EventActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT_ID = "cali.eventkalender.event_id";
    private static final String SAVED_EVENTS = "SAVEDEVENTS";

    private RelativeLayout mRelativeLayout;
    private ViewPager mEventViewPager;
    private CustomViewPager mImageViewPager;
    private ImageAdapter mImageAdapter;
    private CirclePageIndicator mCirclePageIndicator;

    private List<Event> mEvents;

    public static Intent newIntent(Context packageContext, UUID eventId) {
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        if(savedInstanceState != null) {
            mEvents = savedInstanceState.getParcelable(SAVED_EVENTS);
        }
        else {
            mEvents = NationLab.get(this).findAllEvents();
        }

       mRelativeLayout = (RelativeLayout) findViewById(R.id.event_activity_relative_layout);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);

        mEventViewPager = (ViewPager) findViewById(R.id.event_view_pager);
        mEvents = NationLab.get(this).findAllEvents();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mEventViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Event event = mEvents.get(position);
                Nation nation = event.getNation();
                return EventFragment.newInstance(event.getId(), nation.getId());
            }

            @Override
            public int getCount() {
                return mEvents.size();
            }
        });

        for (int i = 0; i < mEvents.size(); i++) {
            if (mEvents.get(i).getId().equals(eventId)) {
                mEventViewPager.setCurrentItem(i);
                break;
            }
        }

        mImageViewPager = (CustomViewPager) findViewById(R.id.image_view_pager);
        mImageAdapter = new ImageAdapter(this);

        mImageViewPager.setAdapter(mImageAdapter);
        mImageViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mImageViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mCirclePageIndicator.setViewPager(mImageViewPager);
    }

}
