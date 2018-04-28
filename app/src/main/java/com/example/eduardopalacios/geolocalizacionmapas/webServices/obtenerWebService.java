package com.example.eduardopalacios.geolocalizacionmapas.webServices;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eduardopalacios.geolocalizacionmapas.example.Example;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardopalacios on 24/04/18.
 */

public class obtenerWebService extends AsyncTask<String,Integer,String> {
    @Override
    protected String doInBackground(String... valores) {

        final String  LATITUD=valores[0];
        final String LONGITUD=valores[1];

        String cadena="http://maps.googleapis.com/maps/api/geocode/json?latlng="+LATITUD+","+LONGITUD+"&sensor=false";

        String direccion="";


        try {

            URL uri=new URL(cadena);
            HttpURLConnection conection=(HttpURLConnection)uri.openConnection();
            conection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

            int respuesta=conection.getResponseCode();

            if (respuesta==HttpURLConnection.HTTP_OK)
            {

                String JSON="";
                String line;
                BufferedReader reader=new BufferedReader(new InputStreamReader(conection.getInputStream()));

                while ((line=reader.readLine())!=null)
                {
                    JSON=JSON+line;
                }

                Gson gson=new Gson();
                Example example=gson.fromJson(JSON, Example.class);



                direccion=  example.getResults().get(0).getFormattedAddress();





            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return direccion;
    }
}
