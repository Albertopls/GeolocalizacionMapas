package com.example.eduardopalacios.geolocalizacionmapas;

import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitud,longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle=getIntent().getExtras();

        double coordenadas[]=bundle.getDoubleArray("COORDENADAS");

        latitud=coordenadas[0];
        longitud=coordenadas[1];



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

        //Tipos de mapas que se pueden utilizar
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //point almacena coordenadas Long/Lat
        //Point point=new Point((int)latitud,(int)longitud);

        // Add a marker in somewhere on the map and move the camera
        LatLng latLng = new LatLng(latitud,longitud);

        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_iconos)));
       // mMap.addMarker(new MarkerOptions().position(latLng).title("marcador 2"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }
}
