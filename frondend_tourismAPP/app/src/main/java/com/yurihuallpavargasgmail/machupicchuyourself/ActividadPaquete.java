package com.yurihuallpavargasgmail.machupicchuyourself;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import JSON.JContenidoPaquete;
import auxiliar.Auxiliar;
import auxiliar.AuxiliarHelper;
import clases.ContenidoPaquete;
import clases.ParcelPaquete;
import conexion.MySocialMediaSingleton;
import fragmento.fragmentoContenidoPaquete;

public class ActividadPaquete extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

        private SliderLayout mDemoSlider;
        private static String idPaquete="";
        private static int  idioma=2;
        private boolean jsoBandera=false;
      ContenidoPaquete [] cpaquete=ContenidoPaquete.c;
    private static final String TAG = "ProductActivity";


    ParcelPaquete product;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.actividad_paquete);

            //resivimos los datos del evento
            product= Bundle();
            //numero de lugares que contiene paquete
            idPaquete=product.getId();
            idioma= AuxiliarHelper.getAuxiliar().getIdioma()+1;
            iniciarCarga();
        }
    //logo de herramienta
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

    @Override
        protected void onStop() {
            // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
            mDemoSlider.stopAutoCycle();
        super.onStop();
    }

        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            Log.d("Slider Demo", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_paquete, menu);
        return true;
    }
    // This could be moved into an abstract BaseActivity
// class for being re-used by several instances
    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.footer, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public ParcelPaquete Bundle()
    {
        return  getIntent().getParcelableExtra("PaqueteSeleccionado");
    }

    //annadir los taps



    public void cargarDatos(String JSON_URL)
    {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsoBandera=new JContenidoPaquete().parseJPaquete(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActividadPaquete.this, getResources().getString(R.string.verifique_conexion), Toast.LENGTH_LONG).show();
                    }
                });

       MySocialMediaSingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private ProgressDialog pDialog;
    private MiTareaAsincronaDialog tarea2;
    private void iniciarCarga()
    {
        pDialog = new ProgressDialog(ActividadPaquete.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage(getResources().getString(R.string.carga));
        pDialog.setCancelable(true);
        pDialog.setMax(100);

        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }

    private void InicioActividad()
    {
        agregarToolbar();

        //System.err.print("******"+item+"-"+ cantidadTuristica);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        //url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        //url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        //url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        //url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        //HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        if(cpaquete[0].getNombre().length>0)
        {
            for(int i=0;i<cpaquete[0].getIdDrawable().length;i++)
            {
                /*
                ejemplo:
                 file_maps.put("Hannibal",R.drawable.machupicchu);
                */
                url_maps.put(cpaquete[0].getNombre()[i],cpaquete[0].getIdDrawable()[i]);
            }
        }else
        {
            url_maps.put(product.getNombre()[Auxiliar.getIdioma()],product.getIdDrawable());
        }


        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        //ingresamos un evento click al botonReservar
        fragmentoContenidoPaquete contPaquete=new fragmentoContenidoPaquete();
        contPaquete.partesTab(product,cpaquete);
        setFragment(contPaquete);
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
            cargarDatos("https://www.e-ranti.com/servicioWeb-1.0/footpathperu/coordenadas/listar/"+idPaquete+"/"+idioma);
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
               // Toast.makeText(ActividadPaquete.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                InicioActividad();

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(ActividadPaquete.this, getResources().getString(R.string.carga_cancelado), Toast.LENGTH_SHORT).show();
        }
    }

}
