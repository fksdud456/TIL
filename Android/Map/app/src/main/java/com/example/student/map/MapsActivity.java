package com.example.student.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

public class MapsActivity extends FragmentActivity {
    private GoogleMap mMap;
    private LinearLayout layout_map;
    private ImageView imageView;
    private WebView webView;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapController = new MapController(mapFragment);
        makeUI();
    }

    private void makeUI() {
        layout_map = findViewById(R.id.layout_map);
        imageView = findViewById(R.id.imageView);
        webView = findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.naver.com/");

        layout_map.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
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
            mapController.addBakeryMarker();
        } else if (v.getId() == R.id.bt_alchole) {
            mapController.addSulzipMarker();
        } else if (v.getId() == R.id.bt_cafe) {
            mapController.addCafeMarker();
        }
    }

}
