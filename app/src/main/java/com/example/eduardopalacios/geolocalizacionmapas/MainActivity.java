package com.example.eduardopalacios.geolocalizacionmapas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduardopalacios.geolocalizacionmapas.webServices.obtenerWebService;
import com.example.eduardopalacios.geolocalizacionmapas.webServices.obtenerWebServiceDireccion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText latitud,longitud,direccion;
    Button btnUbicacion,btnLongLat,btnDireccion;
    obtenerWebService obtenerWebService;
    obtenerWebServiceDireccion obtenerWebServiceDireccion;
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitud=findViewById(R.id.ETLat);
        longitud=findViewById(R.id.ETLong);
        direccion=findViewById(R.id.ETDir);

        btnUbicacion=findViewById(R.id.btn1);
        btnUbicacion.setOnClickListener(this);

        btnLongLat=findViewById(R.id.btn2);
        btnLongLat.setOnClickListener(this);

        btnDireccion=findViewById(R.id.btn3);
        btnDireccion.setOnClickListener(this);

        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }

        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location1) {
                obtenerUbicacion(location1);

                location=location1;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        //locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn1:

                obtenerUbicacion(location);

                break;

            case R.id.btn2:

               obtenerDireccion(latitud.getText().toString(),longitud.getText().toString());


                break;

            case R.id.btn3:


            //StringBuilder stringBuilder=new StringBuilder();

            //stringBuilder.append(direccion.getText().toString());

            //for (int i=0;i<stringBuilder.length();i++)
            //{
              //  if (stringBuilder.charAt(i)==' ')
                //{
                  //  stringBuilder.deleteCharAt(i);
                //}
            //}

            //Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_SHORT).show();


                obtenerLOngLat(direccion.getText().toString());

                break;


                default:

                    break;
        }

    }

    public void obtenerDireccion(String latitud,String longitud)
    {
        String Midireccion="";
        obtenerWebService=new obtenerWebService();
        obtenerWebService.execute(latitud,longitud);
        try {
            Midireccion=obtenerWebService.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),Midireccion,Toast.LENGTH_SHORT).show();
    }

    public void obtenerLOngLat(String direccion)
    {

        double coordenadas[] = new double[2];

        obtenerWebServiceDireccion=new obtenerWebServiceDireccion();
        obtenerWebServiceDireccion.execute(direccion);

        try {
            coordenadas=obtenerWebServiceDireccion.get();


        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }


        Bundle bundle=new Bundle();
        bundle.putDoubleArray("COORDENADAS",coordenadas);
        Intent intent=new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void obtenerUbicacion(Location loc)
    {
        if (loc!=null)
        {
            String miLatitud,miLongitud;
            miLatitud=String.valueOf(loc.getLatitude());
            miLongitud=String.valueOf(loc.getLongitude());

            Toast.makeText(getApplicationContext(),miLatitud+" , "+miLongitud,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"desconocido",Toast.LENGTH_SHORT).show();
        }
    }
}
