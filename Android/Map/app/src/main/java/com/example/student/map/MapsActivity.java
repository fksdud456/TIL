package com.example.student.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LinearLayout layout_map;
    private ImageView imageView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        makeUI();
    }

    private void makeUI() {
        layout_map = findViewById(R.id.layout_map);
        imageView = findViewById(R.id.imageView);
        webView = findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://70.12.114.150/mv");

        layout_map.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void clickBtn(View v) {
        if (v.getId() == R.id.bt_map) {
            layout_map.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.bt_image) {
            layout_map.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            webView.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.bt_chart) {
            layout_map.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
        }
    }

    public void clickMapBtn(View v) {
        if (v.getId() == R.id.bt_bread) {
        } else if (v.getId() == R.id.bt_alchole) {
        } else if (v.getId() == R.id.bt_cafe) {
        }
    }

}
