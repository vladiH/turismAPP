package fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadPagina;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadPrincipal;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import java.util.ArrayList;
import java.util.List;

import clases.ParcelPaquete;

public class fragmentoContenidoReservar extends Fragment implements View.OnClickListener {
    Spinner spQuantity;
    Button botonReservar;
    Button botonAnadir;
    private ParcelPaquete product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragmento_contenido_reservar, container, false);
        botonAnadir=(Button)view.findViewById(R.id.botonAnadir);
        botonReservar=(Button)view.findViewById(R.id.botonReservar);
        spQuantity=(Spinner)view.findViewById(R.id.spQuantity);
        botonAnadir.setOnClickListener(this);
        botonReservar.setOnClickListener(this);
        initializeQuantity(view);
        return view;
    }
    @Override
    public void onClick (View v)
    {
        if (v.getId() == R.id.botonAnadir) {
            // Intent intend=new Intent(this,CarritoPrueba.class);
            //startActivity(intend);
            onOrderProduct();
        }
        if (v.getId() == R.id.botonReservar)
        {
            Reservar();
        }
    }
    private void onOrderProduct() {
        Cart cart = CartHelper.getCart();
        int cantidad=Integer.valueOf(spQuantity.getSelectedItem().toString());
        cart.add(product, cantidad);
        Toast.makeText(getActivity(),String.valueOf(cantidad)+getResources().getString(R.string.anadido_al_carrito), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ActividadPrincipal.class);
        startActivity(intent);
    }

    private void Reservar()
    {
        Intent intent = new Intent(getActivity(), ActividadPagina.class);
        intent.putExtra("IDPAQUETE", product.getId());
        startActivity(intent);
    }
    private void initializeQuantity(View v) {
        List<Integer> QUANTITY_LIST = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) QUANTITY_LIST.add(i);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }
    public void Producto(ParcelPaquete product)
    {
        this.product=product;
    }
}
