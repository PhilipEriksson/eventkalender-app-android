package cali.eventkalender.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cali.eventkalender.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String NATION_NAME = "NATION_NAME";

    private Double mLatitude;
    private Double mLongitude;
    private String mNationName;
    private MarkerOptions mNationMarkerOptions;
    private GoogleMap mMap;

    private Marker mUserMarker;
    private MarkerOptions mUserMarkerOptions;
    private LatLng mUserCoordinates;
    private LocationManager mLocationManager;
    final static int PERMISSION_ALL = 1;
    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    public static Intent newIntent(Context packageContext, Double latitude, Double longitude, String name) {
        Intent intent = new Intent(packageContext, MapsActivity.class);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        intent.putExtra(NATION_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLatitude = getIntent().getDoubleExtra("LATITUDE", 0);
        mLongitude = getIntent().getDoubleExtra("LONGITUDE", 0);
        mNationName = getIntent().getStringExtra("NATION_NAME");

        setTitle(mNationName);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mUserMarkerOptions = new MarkerOptions().position(new LatLng(63.820324, 20.306936)).title("Din plats");
        //MIT-huset Woho!

        LatLng nationPosition = new LatLng(mLatitude, mLongitude);
        mNationMarkerOptions = new MarkerOptions().position(nationPosition).title(mNationName).snippet("Lokaler");

        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        } else requestLocation();
        if (!isLocationEnabled())
            showAlert(1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng nationPosition = new LatLng(mLatitude, mLongitude);

        mUserMarker =  mMap.addMarker(mUserMarkerOptions);

        mMap.addMarker(mNationMarkerOptions);

        // Automatisk inzoomning till min fastnålning
        CameraPosition cameraPosition = new CameraPosition.Builder().target(nationPosition).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onLocationChanged(Location location) {
        mUserCoordinates = new LatLng(location.getLatitude(), location.getLongitude());
        mUserMarker.setPosition(mUserCoordinates);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = mLocationManager.getBestProvider(criteria, true);
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
            mLocationManager.requestLocationUpdates(provider, 10000, 10, this);

    }

    private boolean isLocationEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isPermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Behörighet är given
            return true;
        } else {
           // Behörighet är inte given
            return false;
        }
    }

    private void showAlert(final int status) {
        String message, title, btnText;
        if (status == 1) {
            message = "Din platstjänst är avstängd.\nVänligen starta detta " +
                    "för att använda appen";
            title = "Aktivera platstjänst";
            btnText = "Platstjänst inställningar";
        } else {
            message = "Vänligen tillåt appen att använda platstjänst!";
            title = "Behörighetsåtkomst";
            btnText = "Bevilja";
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        if (status == 1) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        } else
                            requestPermissions(PERMISSIONS, PERMISSION_ALL);
                    }
                })
                .setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }
}







































