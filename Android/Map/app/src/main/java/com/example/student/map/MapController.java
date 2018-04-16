package com.example.student.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapController implements OnMapReadyCallback {
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    public MapController(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(37.47, 127.02756);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker in Kangnam"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

    public void addBakeryMarker() {
        LatLng curPoint = new LatLng(37.05, 128);
        mMap.addMarker(new MarkerOptions().position(curPoint).title("Bakery"));
    }

    public void addSulzipMarker() {
        LatLng curPoint = new LatLng(37.05, 128);
        mMap.addMarker(new MarkerOptions().position(curPoint).title("Sulzip"));
    }

    public void addCafeMarker() {
        LatLng curPoint = new LatLng(37.05, 128);
        mMap.addMarker(new MarkerOptions().position(curPoint).title("Cafe"));
    }
}
