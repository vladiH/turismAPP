package com.yurihuallpavargasgmail.machupicchuyourself;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.Map;


public class ActividadPagina extends AppCompatActivity {
    private static String ID="";
    private static Map<String,Integer> car;
    private ProgressBar progressBar;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ID=getIntent().getStringExtra("IDPAQUETE");
        car=(Map<String,Integer>)getIntent().getSerializableExtra("CARRITO");
        setContentView(R.layout.actividad_pagina);
        agregarToolbar();
        IniciarWeb();
    }

    public void IniciarWeb()
    {
        //habilitamos el java script
        webView = (WebView) this.findViewById(R.id.webviewPagina);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });
        webView.loadUrl("https://e-ranti.com/pricing_fpp/?var1=" + ID);

        //Sincronizamos la barra de progreso de la web
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                ActividadPagina.this.setProgress(progress * 1000);
                progressBar.incrementProgressBy(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }

        });

    }

    //logo de herramienta
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner icono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.indicador_izquierdo);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_pagina, menu);
        return true;
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
    @Override public void onBackPressed() {
        if (webView.canGoBack())
        { webView.goBack(); }
        else { super.onBackPressed(); } }
}
