package fragmento;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.yurihuallpavargasgmail.machupicchuyourself.ActividadPaquete;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adaptador.CartItemAdapter;
import clases.CartItem;
import clases.Paquete;
import clases.ParcelPaquete;

public class fragmentoCarrito extends Fragment {
    private static final String TAG = "ShoppingCartActivity";

    ListView lvCartItems;
    Button bClear;
    Button bShop;
    TextView tvTotalPrice;
    ImageView imagen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_carrito, container, false);



        lvCartItems = (ListView) view.findViewById(R.id.lvCartItems);
        LayoutInflater layoutInflater = inflater;

        final Cart cart = CartHelper.getCart();
        final TextView tvTotalPrice = (TextView) view.findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("$"+String.valueOf(cart.getTotalPrice()));

        
        lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lvCartItems, false));

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(getActivity());
        cartItemAdapter.updateCartItems(getCartItems(cart));

        lvCartItems.setAdapter(cartItemAdapter);

        bClear = (Button) view.findViewById(R.id.bClear);
        bShop = (Button) view.findViewById(R.id.bShop);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                tvTotalPrice.setText("$"+String.valueOf(cart.getTotalPrice()));
            }
        });

        bShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* StrictMode.enableDefaults();
                httpHandler Post=new httpHandler();
                Post.setCarrito(cart.getItemWithQuantity());
                String mensaje=Post.post("https://www.e-ranti.com/pricing_enjoyperuholidays");
                Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();*/
                Toast.makeText(getActivity(), getResources().getString(R.string.proximamente), Toast.LENGTH_LONG).show();
            }
        });

        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity())
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<CartItem> cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position-1).getProduct());
                                cartItems.remove(position-1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                tvTotalPrice.setText("$"+String.valueOf(cart.getTotalPrice()));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();
                return false;
            }
        });

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                ParcelPaquete product = cartItems.get(position-1).getProduct();
                //Log.d(TAG, "Viewing product: " + product.getName());
                //bundle.putSerializable("product", product);
                Intent intent = new Intent(getActivity(), ActividadPaquete.class);
                intent.putExtra("PaqueteSeleccionado", product);
                startActivity(intent);
            }
        });





        return view;
    }


    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<String, Integer> itemMap = cart.getItemWithQuantity();

        for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            int i=BuscarIndice(entry.getKey());
            if(i!=-1)
            {
                //cartItem.setProduct((ParcelPaquete) Paquete.getPAQUETES().get(Integer.valueOf(entry.getKey())));
                cartItem.setProduct((ParcelPaquete) Paquete.getPAQUETES().get(i));
                cartItem.setQuantity(entry.getValue());
                cartItems.add(cartItem);
            }

        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }

    public int BuscarIndice(String id)
    {
        for(int i=0;i<Paquete.getPAQUETES().size();i++)
        {
            if(Paquete.getPAQUETES().get(i).getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}


