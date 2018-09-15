package com.softdev.smartraysam.carfeeds;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softdev.smartraysam.carfeeds.model.carModel;

import java.util.ArrayList;
import static com.softdev.smartraysam.carfeeds.MainActivity.carModels;
import static com.softdev.smartraysam.carfeeds.MainActivity.selectCar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Double lat, lng;
    String selectedCar;
    ArrayList<carModel> feedList;
    carModel cModel;
    LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        selectedCar= getIntent().getStringExtra(selectCar);
//       =(ArrayList<carModeldfd>) getIntent().getSerializableExtra(carModels);
         feedList = getIntent().getParcelableArrayListExtra(carModels);

    }
    @Override
    public void onResume() {
        super.onResume();
        mapFragment.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapFragment.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapFragment.onStop();
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
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapType(mMap.MAP_TYPE_NORMAL);
        for (int i = 0; i < feedList.size(); i++) {
            cModel= feedList.get(i);
            lat=cModel.getCoordinates().get(1);
            lng=cModel.getCoordinates().get(0);
            loc = new LatLng(lat, lng);
            if(cModel.getName().equals(selectedCar)){
                Marker mMarker = mMap.addMarker(new MarkerOptions()
                        .position(loc)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carmarker))
                        .title(cModel.getName())
                        .visible(true)
                        .anchor(0.5f, 1));
                mMarker.setTag(i);
                mMarker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,15));
            }
            else{
                Marker mMarker = mMap.addMarker(new MarkerOptions()
                        .position(loc)
                        .title(cModel.getName())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carmarker))
                        .anchor(0.5f, 1));
                mMarker.setTag(i);
            }


        }

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapFragment.onLowMemory();

    }

}
