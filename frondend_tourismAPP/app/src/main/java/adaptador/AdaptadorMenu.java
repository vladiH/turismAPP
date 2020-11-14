package adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadPaquete;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import clases.Menu;
import clases.MenuHelper;

/**
 * Created by YURI VLADIMIR on 27/03/2017.
 */

    public class AdaptadorMenu
            extends RecyclerView.Adapter<AdaptadorMenu.ViewHolder> {
        // private static OnItemClickListener onItemClickListener;
        int selected_position = 0;
        Context context;
        public static final String MenuSeleccionado="MenuSeleccionado";
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Campos respectivos de un item
            public ImageView imagen_menu_fondo;
            public ImageView icono_menu;
            public TextView nombre_menu;
            public ViewHolder(View v) {
                super(v);
                imagen_menu_fondo = (ImageView) v.findViewById(R.id.imagen_menu_fondo);
                icono_menu = (ImageView) v.findViewById(R.id.icono_menu);
                nombre_menu = (TextView) v.findViewById(R.id.nombre_menu);
            }
        }

        public AdaptadorMenu() {

        }

        @Override
        public int getItemCount() {
            return MenuHelper.getMenu().getMenus().size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_lista_menu, viewGroup, false);
            context=viewGroup.getContext();
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            Menu item = MenuHelper.getMenu().getMenus().get(i);
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
                    .load(item.getImagen_menu_fondo())
                    .centerCrop()
                    .into(viewHolder.imagen_menu_fondo);
            Glide.with(viewHolder.itemView.getContext())
                    //inicio de cartviews = 0 identificador del pquete
                    //.load(item.getIdDrawable()[0])
                    .load(item.getIcono_menu())
                    .centerCrop()
                    .into(viewHolder.icono_menu);
            viewHolder.nombre_menu.setText(item.getNombre_menu());
        }
        public void enviarDatos( int i, ViewHolder viewHolder)
        {
            Context contex;
            contex=viewHolder.itemView.getContext();
            Intent a=new Intent(contex.getApplicationContext(), ActividadPaquete.class);
            Menu menu = MenuHelper.getMenu().getMenus().get(i);
            a.putExtra(MenuSeleccionado, menu.getId());
            contex.startActivity(a);

        }

    }
