package com.example.joebo.beachbud;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {            //google maps implementation of the api using google how to tutorial
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //ads markers where loca beaches are plus their name, can choose to navigate to beach in the app.
        LatLng ainsdale = new LatLng(53.607961, -3.064298);
        mMap.addMarker(new MarkerOptions().position(ainsdale).title("Ainsdale Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ainsdale));

        LatLng southport = new LatLng(53.652873, -3.02160);
        mMap.addMarker(new MarkerOptions().position(southport).title("Southport Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(southport));

        LatLng formby = new LatLng(53.541072, -3.096406);
        mMap.addMarker(new MarkerOptions().position(formby).title("Formby Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(formby));

        LatLng hoylake = new LatLng(53.393585, -3.176424);
        mMap.addMarker(new MarkerOptions().position(hoylake).title("Hoylake Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hoylake));

        LatLng nBrighton = new LatLng(53.431109, -3.060987);
        mMap.addMarker(new MarkerOptions().position(nBrighton).title("New Brighton Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nBrighton));

        LatLng wKirby = new LatLng(53.362586, -3.177502);
        mMap.addMarker(new MarkerOptions().position(wKirby).title("West Kirby Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wKirby));

        LatLng moreton = new LatLng(53.424865, -3.088389);
        mMap.addMarker(new MarkerOptions().position(moreton).title("Moreton Beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(moreton));

    }
}
