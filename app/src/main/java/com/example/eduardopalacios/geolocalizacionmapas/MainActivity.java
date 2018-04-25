package com.example.eduardopalacios.geolocalizacionmapas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText latitud,longitud,direccion;
    Button btnUbicacion,btnLongLat,btnDireccion;
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
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn1:

                break;

            case R.id.btn2:
                break;

            case R.id.btn3:
                break;


                default:

                    break;
        }

    }
}
