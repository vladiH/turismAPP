package com.yurihuallpavargasgmail.machupicchuyourself;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import JSON.JCoordenadas;
import clases.Coordenadas;
import conexion.MySocialMediaSingleton;

public class ActividadMapa extends AppCompatActivity implements OnMapReadyCallback {
    Context context;
    private static String JSON_URL = "";
    private boolean jsoBandera=false;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //optenemos la coordenadas del paquete seleccionado
    Coordenadas [] coordenada=Coordenadas.c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mapa);
        context=getBaseContext();
        JSON_URL=getURL();
        iniciarCarga();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
       UiSettings uiSettings = mMap.getUiSettings();
        //uiSettings.setScrollGesturesEnabled(false);
       // uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        //Coordenadas c=new Coordenadas();
        mMap.setMapType(mMap.MAP_TYPE_NORMAL);
        if(coordenada[0].getTitulo().size()>1)
        {
            Marcar();
        }else{
            if(coordenada[0].getTitulo().size()==1)
            {
                Marcar1();
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner icono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.indicador_izquierdo);
            ab.setBackgroundDrawable(new ColorDrawable());
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }
    public void Marcar1()
    {
        //coordenadas en el mapa
        LatLng a=null;
        for(int i=0;i<coordenada[0].getTitulo().size();i++)
        {
            a=new LatLng(coordenada[0].getLatitud().get(i), coordenada[0].getAltitud().get(i));
            mMap.addMarker(new MarkerOptions()
                    .position(a)
                    .title(coordenada[0].getTitulo().get(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coordenada[0].getLatitud().get(0), coordenada[0].getAltitud().get(0))));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(coordenada[0].getLatitud().get(0), coordenada[0].getAltitud().get(0)))
                .zoom(9)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void Marcar()
    {
        //coordenadas en el mapa
        LatLng a=null;
        LatLng b=null;
        //inicialiazar polilinea para el trazado de coordenadas
       // PolylineOptions paquete = new PolylineOptions();
        for(int i=0;i<coordenada[0].getTitulo().size();i++)
        {
            if(i==0)
            {
                a=new LatLng(coordenada[0].getLatitud().get(i), coordenada[0].getAltitud().get(i));
                mMap.addMarker(new MarkerOptions()
                        .position(a)
                        .title(coordenada[0].getTitulo().get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.inicio_m)));
            }
            if(i!=0 && i!=coordenada[0].getTitulo().size()-1)
            {
                a=new LatLng(coordenada[0].getLatitud().get(i), coordenada[0].getAltitud().get(i));
                mMap.addMarker(new MarkerOptions()
                        .position(a)
                        .title(coordenada[0].getTitulo().get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa)));
            }
            if(i==coordenada[0].getTitulo().size()-1)
            {
                a=new LatLng(coordenada[0].getLatitud().get(i), coordenada[0].getAltitud().get(i));
                mMap.addMarker(new MarkerOptions()
                        .position(a)
                        .title(coordenada[0].getTitulo().get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_m)));
            }
            //Polilineas(paquete,a);
            if(i<coordenada[0].getTitulo().size()-1)
            {
                b=new LatLng(coordenada[0].getLatitud().get(i+1), coordenada[0].getAltitud().get(i+1));
                DibujarTrazos(a,b);
            }
        }

        //dar color a la linea trazada
       // paquete.color(getResources().getColor(R.color.trazo_mapa));    // Rojo 500
     //   Polyline polyline = mMap.addPolyline(paquete);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coordenada[0].getLatitud().get(0), coordenada[0].getAltitud().get(0))));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(coordenada[0].getLatitud().get(0), coordenada[0].getAltitud().get(0)))
                .zoom(7)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
   /* public void Polilineas(PolylineOptions paquete, LatLng coordenada)
    {
        paquete.add(coordenada);

    }*/
    public void DibujarTrazos( LatLng a,  LatLng b)
    {
        List<String> XY=ecuacionRecta(a.latitude, a.longitude, b.latitude, b.longitude);
        for(int i=0;i<XY.size()/2;i++)
        {
            String [] latlon=XY.get(i*2).split("/");
            double lat1=Double.parseDouble(latlon[0]);
            double lon1=Double.parseDouble(latlon[1]);
            String [] latlon1=XY.get(i*2+1).split("/");
            double lat2=Double.parseDouble(latlon1[0]);
            double lon2=Double.parseDouble(latlon1[1]);
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(lat1,lon1), new LatLng(lat2,lon2))
                    .width(6)
                    .color(Color.BLUE));
        }
    }
    public List<String> ecuacionRecta(double lat1, double lon1, double lat2, double lon2)
    {
        double X=lat1;
        double Y=lon1;
        double dist=distancia(lat1, lon1, lat2, lon2);
        double m=pendiente(lat1,lon1,lat2,lon2);
        List<String> puntosXY=new ArrayList<>();
        puntosXY.add(String.valueOf(X)+"/"+String.valueOf(Y));
        if(lat2>lat1)
        {
            for(int i=0; i<19;i++)
            {
                X=X+dist;
                double y=((X-lat1)*m)+lon1;
                puntosXY.add(String.valueOf(X)+"/"+String.valueOf(y));

            }
        }else
        {
            for(int i=0; i<19;i++)
            {
                X=X-dist;
                double y=((X-lat2)*m)+lon2;
                puntosXY.add(String.valueOf(X)+"/"+String.valueOf(y));

            }
        }
        return puntosXY;
    }

    public double distancia(double lat1, double lon1, double lat2, double lon2)
    {
        //double dist=Math.sqrt(Math.pow((lon2-lon1),2)+ Math.pow((lat2-lat1),2));
        double distX=lat2-lat1;
        if(distX<0)
        {
            distX=distX*-1;
        }
        return distX/20;
    }

    public double pendiente(double lat1, double lon1, double lat2, double lon2)
    {
        double m=1;
        if(0!=(lat2-lat1)){
            m=(lon2-lon1)/(lat2-lat1);
        }
        return m;
    }

    public void cargarDatos()
    {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsoBandera=new JCoordenadas().parseJCoordenada(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActividadMapa.this,getResources().getString(R.string.verifique_conexion), Toast.LENGTH_LONG).show();
                    }
                });

        MySocialMediaSingleton.getInstance(context).addToRequestQueue(stringRequest);
        //jsoBandera=true;
    }

    public String getURL()
    {
        return  getIntent().getStringExtra("URL");
    }
    /////////////////////////////////////////////////////////////////////////////////////
    private ProgressDialog pDialog;
    private MiTareaAsincronaDialog tarea2;
    private void iniciarCarga()
    {
        pDialog = new ProgressDialog(ActividadMapa.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage(getResources().getString(R.string.carga));
        pDialog.setCancelable(true);
        pDialog.setMax(100);

        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }

    private void InicioActividad()
    {
        setUpMapIfNeeded();
        agregarToolbar();
    }
    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            while(jsoBandera==false){

                //  publishProgress(i*10);
                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            cargarDatos();
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();
                //Toast.makeText(ActividadPrincipal.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                InicioActividad();

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(ActividadMapa.this, getResources().getString(R.string.carga_cancelado), Toast.LENGTH_SHORT).show();
        }
    }
}
