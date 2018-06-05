package com.example.truonghaophu.doan;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class BasicMapDemoActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    private File tempFile = new File("/sdcard/.a.jpg");
    Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
       // map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        drawMarker(map);
    }
    public void drawMarker(GoogleMap map) {
        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle_shape);
        Intent intent = getIntent();
        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
        tempFile = new File(intent.getStringExtra("data"));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        map.addMarker(new MarkerOptions()
                .position(new LatLng(41.906991, 12.453360))
                .title("My Marker")
                .icon(markerIcon)
        );
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}