package com.softdev.smartraysam.carfeeds

import android.support.v4.app.FragmentActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.softdev.smartraysam.carfeeds.model.carModel
import java.util.ArrayList

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var mMap: GoogleMap? = null
    private var lat: Double? = null
    private var lng: Double? = null
    private var selectedCar: String = ""
    private var feedList: ArrayList<carModel> = arrayListOf<carModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        selectedCar = intent.getStringExtra("CAR_POSITION")
        feedList = intent.getParcelableArrayListExtra("CAR_MODEL")
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.uiSettings.isMapToolbarEnabled = false
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
        for (i in feedList.indices) {
            val cModel = feedList[i]
            lat = cModel.coordinates[1]
            lng = cModel.coordinates[0]
            val loc = LatLng(lat!!, lng!!)
            if (cModel.name == selectedCar) {
                val mMarker = mMap!!.addMarker(MarkerOptions()
                        .position(loc)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carmarker))
                        .title(cModel.name)
                        .visible(true)
                        .anchor(0.5f, 1f))
                mMarker.tag = i
                mMarker.showInfoWindow()
                mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15f))
            } else {
                val mMarker = mMap!!.addMarker(MarkerOptions()
                        .position(loc)
                        .title(cModel.name)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carmarker))
                        .anchor(0.5f, 1f))
                mMarker.tag = i
            }
        }

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        marker.showInfoWindow()
        return false
    }

}
