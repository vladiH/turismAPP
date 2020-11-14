package com.yurihuallpavargasgmail.machupicchuyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Locale;

import auxiliar.AuxiliarHelper;

/**
 * Created by YURI VLADIMIR on 01/03/2017.
 */
public class ActividadSplash extends Activity {
    // Duracion en milisegundos que se mostrara el splash
    private  int DURACION_SPLASH = 3000; // 3 segundos
    private boolean estado=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String i=Locale.getDefault().getDisplayLanguage().substring(0,3);
        if(i.equals("esp"))
        {
            AuxiliarHelper.getAuxiliar().setIdioma("es");
        }else if(i.equals("Eng"))
        {
            AuxiliarHelper.getAuxiliar().setIdioma("in");
        }else if(i.equals("por"))
        {
            AuxiliarHelper.getAuxiliar().setIdioma("pt");
        }else{
            AuxiliarHelper.getAuxiliar().setIdioma("in");
        }
       // Toast.makeText(ActividadSplash.this, i, Toast.LENGTH_LONG).show();
        // Tenemos una plantilla llamada splash.xml donde mostraremos la informacion que queramos (logotipo, etc.)
        setContentView(R.layout.actividad_splash);
        ImageView imagen=(ImageView)findViewById(R.id.imagenSplash);
        imagen.setImageDrawable(getResources().getDrawable(R.drawable.logofootpath));
        new Handler().postDelayed(new Runnable(){

            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicacion
                Intent intent = new Intent(ActividadSplash.this, ActividadPrincipal.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
