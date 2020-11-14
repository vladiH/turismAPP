package adaptador;

/**
 * Created by YURI VLADIMIR on 21/01/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadCarrito;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadMapa;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadPaquete;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import auxiliar.AuxiliarHelper;
import clases.Paquete;
import clases.PaqueteHelper;
import clases.ParcelPaquete;

/**
 * Adaptador para mostrar los paquetes turisticos en la seccion "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {
   // private static OnItemClickListener onItemClickListener;
    int selected_position = 0;
    Context context;
   public static final String PaqueteSeleccionado="PaqueteSeleccionado";
    public  class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView about;
        public ImageView mapa;
        public TextView nombre;
        public TextView dias;
        public ImageView imagen;
        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_paquete);
            dias=(TextView)v.findViewById(R.id.dias);
            imagen = (ImageView) v.findViewById(R.id.miniatura_paquetes);
            mapa=(ImageView)v.findViewById(R.id.mapa);
            about=(ImageView)v.findViewById(R.id.about);
        }
    }

    public AdaptadorInicio() {

    }

    @Override
    public int getItemCount() {
        return Paquete.getPAQUETES().size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
       context=viewGroup.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        ParcelPaquete item = PaqueteHelper.getPaquete().getPAQUETES().get(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creamos los parametros de envio
                // Updating old as well as new positions
                notifyItemChanged(selected_position);
                selected_position = i;
                notifyItemChanged(selected_position);
                //llamamos a otra actividad desde el RecyclerView
                enviarDatos(i, viewHolder);

            }
        });

        Glide.with(viewHolder.itemView.getContext())
                //inicio de cartviews = 0 identificador del pquete
                //.load(item.getIdDrawable()[0])
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre()[AuxiliarHelper.getAuxiliar().getIdioma()]);
        viewHolder.dias.setText(item.getDias() +" "+ context.getResources().getString(R.string.dias) + " + " + item.getNoches() +" "+ context.getResources().getString(R.string.noches));
        viewHolder.mapa.setImageResource(android.R.drawable.ic_dialog_map);
        viewHolder.mapa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onItemTouchListener(v, i);
                return false;
            }
        });
        viewHolder.about.setImageResource(android.R.drawable.ic_menu_add);
        viewHolder.about.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onItemTouchListener(v, i);
                return false;
            }
        });


    }
    public void abrirMapa(int i)
    {
        String codigo=PaqueteHelper.getPaquete().getPAQUETES().get(i).getId();
            Intent a=new Intent(context.getApplicationContext(), ActividadMapa.class);
            a.putExtra("URL","https://www.e-ranti.com/servicioWeb-1.0/footpathperu/coordenadas/listar/"+codigo);
            //ParcelPaquete paquete = Paquete.getPAQUETES().get(i);
            //a.putExtra(PaqueteSeleccionado, paquete);
            context.startActivity(a);

    }
    public void abrirCarrito(int i)
    {
        Intent a=new Intent(context.getApplicationContext(), ActividadCarrito.class);
        //ParcelPaquete paquete = Paquete.getPAQUETES().get(i);
        //a.putExtra(PaqueteSeleccionado, paquete);
        context.startActivity(a);
    }
    public void enviarDatos( int i, ViewHolder viewHolder)
    {
        Context contex;
        contex=viewHolder.itemView.getContext();
        Intent a=new Intent(contex.getApplicationContext(), ActividadPaquete.class);
        ParcelPaquete paquete = Paquete.getPAQUETES().get(i);
        a.putExtra(PaqueteSeleccionado, paquete);
        contex.startActivity(a);

    }
    public void onItemTouchListener(View v, int i)
    {
        if(v.findViewById(R.id.mapa)==v)
        {
            abrirMapa(i);
        }
        if(v.findViewById(R.id.about)==v)
        {
            abrirCarrito(i);
        }
    }

}