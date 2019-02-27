package cali.eventkalender.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.CirclePageIndicator;


import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.activity.MapsActivity;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;
import cali.eventkalender.utils.CustomScrollView;
import cali.eventkalender.utils.CustomViewPager;
import cali.eventkalender.utils.ImageAdapter;

public class NationFragment extends Fragment {

    private static final String ARG_NATION_ID = "nation_id";
    private static final String SAVED_NATION = "SAVED_NATION";

    private CustomScrollView mNationScrollView;
    private LinearLayout mScrollViewChild;

    private Nation mNation;
    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private TextView mEventTypeTextView;
    private TextView mMusicTypeTextView;
    private TextView mAddressTextView;

    private FloatingActionButton mMapButton;
    private Button mFacebookButton;
    private ImageAdapter mImageAdapter;
    private CirclePageIndicator mCirclePageIndicator;
    private CustomViewPager mImageViewPager;

    MapView mMapView;
    private GoogleMap mGoogleMap;

    public static NationFragment newInstance(UUID nationId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NATION_ID, nationId);
        NationFragment fragment = new NationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            mNation = savedInstanceState.getParcelable(SAVED_NATION);
            for(Event event: mNation.getEvents()) {
                event.setNation(mNation);
            }
        }
        else {
              UUID nationId = (UUID) getArguments().getSerializable(ARG_NATION_ID);
              mNation = NationLab.get(getActivity()).getNation(nationId);
        }

     //   UUID nationId = (UUID) getArguments().getSerializable(ARG_NATION_ID);
     //   mNation = NationLab.get(getActivity()).getNation(nationId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nation, container, false);

        mNationScrollView = (CustomScrollView) view.findViewById(R.id.fragment_scrollView);
        mScrollViewChild = (LinearLayout) view.findViewById(R.id.scrollView_child);
        mNationScrollView.removeAllViews();
        mNationScrollView.addView(mScrollViewChild);

        mNameTextView = (TextView) view.findViewById(R.id.nation_name);
        mNameTextView.setText(mNation.getName());

        mFacebookButton = (Button) view.findViewById(R.id.nation_facebook_button);
        mFacebookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.cs.umu.se/";  // Detta läggs in i databasen sen för unika nationer
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        mDescriptionTextView = (TextView) view.findViewById(R.id.nation_description);
        mDescriptionTextView.setText("Om: \n" + mNation.getAboutNation());

        mEventTypeTextView = (TextView) view.findViewById(R.id.nation_event_types);
        mEventTypeTextView.setText("Typ av Evenemang: \nKlubb, Brunch, Bar, Restaurang");

        mMusicTypeTextView = (TextView) view.findViewById(R.id.nation_music);
        mMusicTypeTextView.setText("Musik: \nHouse, Hip Hop, RnB");

        mAddressTextView = (TextView) view.findViewById(R.id.nation_address);
        mAddressTextView.setText("Adress: \n" + mNation.getAddress());

        updateUI(view, savedInstanceState);

        return view;
    }

    private void updateUI(View view, Bundle savedInstanceState) {
        updateViewPager(view);
        updateMapUI(view, savedInstanceState);
    }

    private void updateViewPager(View view){

        mImageViewPager = (CustomViewPager) view.findViewById(R.id.image_view_pager);
        mImageAdapter = new ImageAdapter(getContext());

        mImageViewPager.setAdapter(mImageAdapter);
        mImageViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mImageViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mCirclePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        mCirclePageIndicator.setViewPager(mImageViewPager);

    }

    private void updateMapUI(View view, Bundle savedInstanceState) {

        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // För att kartan ska visas direkt

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                // Fastnåla position på kartan
                LatLng nationPosition = new LatLng(mNation.getLatitude(), mNation.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().position(nationPosition).title(mNation.getName()).snippet("Lokaler"));

                // Automatisk inzoomning till min fastnålning
                CameraPosition cameraPosition = new CameraPosition.Builder().target(nationPosition).zoom(15).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        mMapButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LatLng nationPosition = new LatLng(mNation.getLatitude(), mNation.getLongitude());
                Intent intent = MapsActivity.newIntent(getContext(), mNation.getLatitude(), mNation.getLongitude(), mNation.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null){
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mMapView != null){
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mMapView != null){
            try {
                mMapView.onDestroy();
            } catch (NullPointerException e) {
                Log.e("Ops", "Error while attempting MapView.onDestroy(), ignoring exception", e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null){
            mMapView.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_NATION, mNation);
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }

}
