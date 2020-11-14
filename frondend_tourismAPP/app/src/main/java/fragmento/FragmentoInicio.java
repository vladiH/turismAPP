package fragmento;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import JSON.JPaquete;
import adaptador.AdaptadorInicio;
import auxiliar.AuxiliarHelper;
import clases.PaqueteHelper;
import conexion.MySocialMediaSingleton;

public class FragmentoInicio extends Fragment {
    private boolean jsoBandera=false;
    private static  String JSON_URL = "";
    private SwipeRefreshLayout refreshLayout;
    //inicializacion de variables
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;

    public FragmentoInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        //layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(1);
        reciclador.setLayoutManager(layoutManager);
        adaptador = new AdaptadorInicio();
        adaptador.notifyDataSetChanged();
        reciclador.setAdapter(adaptador);

        // Obtener el refreshLayout
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

// Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(AuxiliarHelper.getAuxiliar().getContador()>AuxiliarHelper.getAuxiliar().getPaginas())
                        {
                            iniciarCarga();
                        }else{
                            refreshLayout.setRefreshing(false);
                            AuxiliarHelper.getAuxiliar().setContador(4);
                            Toast.makeText(getContext(), getResources().getString(R.string.paquete_existe), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        return view;
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
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), getResources().getString(R.string.principal_conexion), Toast.LENGTH_LONG).show();
                        if( AuxiliarHelper.getAuxiliar().isModificado())
                        {
                            AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador() + AuxiliarHelper.getAuxiliar().getPaginas());
                            AuxiliarHelper.getAuxiliar().setModificado(false);
                        }
                    }
                });

        MySocialMediaSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
    private MiTareaAsincronaDialog tarea2;
    private void iniciarCarga()
    {
        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
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

        }

        @Override
        protected void onPreExecute() {
                AuxiliarHelper.getAuxiliar().setModificado(true);
                AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador() - AuxiliarHelper.getAuxiliar().getPaginas());
                JSON_URL = "https://www.e-ranti.com/servicioWeb-1.0/footpathperu/producto/listar/inicio/"+
                        AuxiliarHelper.getAuxiliar().getContador()+"/"+AuxiliarHelper.getAuxiliar().getPaginas();
                cargarDatos1();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                refreshLayout.setRefreshing(false);
                if( AuxiliarHelper.getAuxiliar().isModificado())
                {
                    AuxiliarHelper.getAuxiliar().setModificado(false);
                }
            }
        }

        @Override
        protected void onCancelled() {
            if( AuxiliarHelper.getAuxiliar().isModificado())
            {
                AuxiliarHelper.getAuxiliar().setContador(AuxiliarHelper.getAuxiliar().getContador() + AuxiliarHelper.getAuxiliar().getPaginas());
                AuxiliarHelper.getAuxiliar().setModificado(false);
            }
            Toast.makeText(getContext(), getResources().getString(R.string.carga_cancelado), Toast.LENGTH_SHORT).show();
        }
    }
}
