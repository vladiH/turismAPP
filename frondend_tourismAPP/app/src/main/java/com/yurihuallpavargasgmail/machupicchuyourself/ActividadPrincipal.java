package com.yurihuallpavargasgmail.machupicchuyourself;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import JSON.JPaquete;
import auxiliar.AuxiliarHelper;
import auxiliar.BanderasHelper;
import clases.PaqueteHelper;
import conexion.MySocialMediaSingleton;
import fragmento.FragmentoInicio;
import fragmento.fragmentoCarrito;
import fragmento.fragmentoContenidoAbout;
import fragmento.fragmento_redes_sociales;


public class ActividadPrincipal extends AppCompatActivity implements View.OnClickListener{

    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    boolean click = false;
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean jsoBandera=false;
    private static  String JSON_URL = "https://www.e-ranti.com/servicioWeb-1.0/footpathperu/producto/listar/inicio/"+AuxiliarHelper.getAuxiliar().getContador()+"/"+0;
    Context context;
    private DrawerLayout drawerLayout;
    MenuItem menu;
    Fragment fragmentoGenerico = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        context=getBaseContext();
        if(new BanderasHelper().getBandera().isBanderaInicio()==false)
        {
            iniciarCarga();
            new BanderasHelper().getBandera().setBanderaInicio(true);
        }else
        {
            InicioActividad();
        }
    }


    //logo de herramienta
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner icono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast.makeText(ActividadPrincipal.this, R.string.submitted, Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", true);
                searchView.setIconified(true);
                AuxiliarHelper.getAuxiliar().setModificado(false);

                JSON_URL = Uri.encode("https://www.e-ranti.com/servicioWeb-1.0/footpathperu/producto/listar/" + query + "/" + AuxiliarHelper.getAuxiliar().getIdioma() + 1, ALLOWED_URI_CHARS);
                jsoBandera = false;
                iniciarCarga();
                new BanderasHelper().getBandera().setBanderaInicio(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                AuxiliarHelper.getAuxiliar().setModificado(false);
                JSON_URL = "https://www.e-ranti.com/servicioWeb-1.0/footpathperu/producto/listar/inicio/"+AuxiliarHelper.getAuxiliar().getContador()+"/"+AuxiliarHelper.getAuxiliar().getPaginas();
                jsoBandera=false;
                iniciarCarga();
                new BanderasHelper().getBandera().setBanderaInicio(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //configurar todo los atributos antes de inicializar
    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    /*
                    nos provee un controlador para la seleccion.
                    Dentro de este recibimos un objeto MenuItem para extraer toda la informacion del item seleccionado.
                    Con setChecked() sombreamos el elemento seleccionado si usas true como parametro.
                    La seleccion implica crear el contenido necesario de la seccion,
                    por lo que tenemos el metodo seleccionarItem() para ello.
                    * */
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    //seleccion de items
    //nos indica que fragmento hemos inicializado
    //y poder mostrar al seleccionar
    private void seleccionarItem(MenuItem itemDrawer) {
        fragmentoGenerico = null;
        fragmentManager = getSupportFragmentManager();


        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                if (fab != null) {
                    fab.show();
                }
                break;
            case R.id.item_cuenta:
                fragmentoGenerico=new fragmentoCarrito();
                if (fab != null) {
                    fab.hide();
                }
                break;
            case R.id.item_categorias:
                fragmentoGenerico=new fragmentoContenidoAbout();
                if (fab != null) {
                    fab.hide();
                }
                break;
            case R.id.item_configuracion:
                fragmentoGenerico=new fragmento_redes_sociales();
                if (fab != null) {
                    fab.hide();
                }
               break;
        }
        //remplaza la vista de fragmentos
        RemplazarFragmento(fragmentoGenerico, fragmentManager, itemDrawer);
    }

    @Override
    public void onClick(View view) {
      /*  int id=view.getId();
        if(id==R.id.icono_carrito){
           // fragmentoGenerico=new fragmentoCarrito();
           // fragmentManager = getSupportFragmentManager();
         //RemplazarFragmento(fragmentoGenerico,fragmentManager,menu);
        }
        if(id==R.id.carritoVisible){
            fragmentoGenerico=new fragmentoCarrito();
            fragmentManager = getSupportFragmentManager();
            RemplazarFragmento(fragmentoGenerico,fragmentManager,menu);
        }*/

    }
    public void IniciarBotonFlotante()
    {
         fab = (FloatingActionButton) findViewById(R.id.fab);
         //fab.setImageResource(R.drawable.ic_agregar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(PaqueteHelper.getPaquete().getPAQUETES().size()<4){
                  Toast.makeText(ActividadPrincipal.this, getResources().getString(R.string.paquete_existe), Toast.LENGTH_SHORT).show();
              }else{
                  click = !click;
                  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                      Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                              android.R.interpolator.fast_out_slow_in);

                      view.animate()
                              .rotation(click ? 90f : 0)
                              .setInterpolator(interpolador)
                              .start();
                  }
                  AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador()+AuxiliarHelper.getAuxiliar().getPaginas());
                  AuxiliarHelper.getAuxiliar().setModificado(true);
                  JSON_URL = "https://www.e-ranti.com/servicioWeb-1.0/footpathperu/producto/listar/inicio/"+AuxiliarHelper.getAuxiliar().getContador()+"/"+AuxiliarHelper.getAuxiliar().getPaginas();
                  jsoBandera=false;
                  iniciarCarga();
                  new BanderasHelper().getBandera().setBanderaInicio(true);
              }
            }
        });
    }

    public void CargarPrecio()
    {
        final Cart cart = CartHelper.getCart();
        TextView precio=(TextView)findViewById(R.id.texto_total_carrito);
        precio.setText("$" + String.valueOf(cart.getTotalPrice()));
    }

    public void RemplazarFragmento(Fragment fg, FragmentManager fm, MenuItem m)
    {
        if (fg != null) {
            fm
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fg)
                    .commit();
        }

        // Setear titulo actual
        setTitle(m.getTitle());
    }

    public void cargarDatos()
    {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      jsoBandera=new JPaquete().parseJPaquete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(ActividadPrincipal.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

       MySocialMediaSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void cargarDatos1()
    {
        PaqueteHelper.getPaquete().PAQUETES.clear();
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsoBandera=new JPaquete().parseJPaquete(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if( AuxiliarHelper.getAuxiliar().isModificado())
                        {
                            AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador() - AuxiliarHelper.getAuxiliar().getPaginas());
                            AuxiliarHelper.getAuxiliar().setModificado(false);
                        }
                        pDialog.dismiss();
                        Toast.makeText(ActividadPrincipal.this, getResources().getString(R.string.principal_conexion), Toast.LENGTH_LONG).show();
                    }
                });

        MySocialMediaSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private ProgressDialog pDialog;
    private MiTareaAsincronaDialog tarea2;
    private void iniciarCarga()
    {
        IniciarDialog();
        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }
    public void IniciarDialog()
    {
        pDialog = new ProgressDialog(ActividadPrincipal.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage(getResources().getString(R.string.carga));
        pDialog.setCancelable(true);
        pDialog.setMax(100);
    }

    private void InicioActividad()
    {
        agregarToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //verificamos si el navigation view es nulo o no para
        //inicializar
        if (navigationView != null) {

            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
        //cargar precio del carrito
        CargarPrecio();
        IniciarBotonFlotante();
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
            if(new BanderasHelper().getBandera().isBanderaInicio()==true)
            {
                cargarDatos1();
            }else
            {
                cargarDatos();
            }
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
                if( AuxiliarHelper.getAuxiliar().isModificado())
                {
                    AuxiliarHelper.getAuxiliar().setModificado(false);
                }
                //Toast.makeText(ActividadPrincipal.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                InicioActividad();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(ActividadPrincipal.this, getResources().getString(R.string.carga_cancelado), Toast.LENGTH_SHORT).show();
            if( AuxiliarHelper.getAuxiliar().isModificado())
            {
                AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador() - AuxiliarHelper.getAuxiliar().getPaginas());
                AuxiliarHelper.getAuxiliar().setModificado(false);
            }
        }
    }
}
