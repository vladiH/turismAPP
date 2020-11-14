package fragmento;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

import clases.ContenidoPaquete;

public class fragmentoDescripcion extends Fragment {

    public fragmentoDescripcion() {
        // Required empty public constructor
    }

    private ContenidoPaquete []cpaquete;
    public void Producto(ContenidoPaquete [] cpaquete)
    {
        this.cpaquete=cpaquete;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragmento_descripcion, container, false);
        cargarHtmlCodigo(view);
        return view;
    }
    public void cargarHtmlCodigo(View v)
    {
        // 0 nos indica el idioma
        String html=cpaquete[0].getDescripcion();
        final String MIME_TYPE = "text/html";
        WebView web_view_load_data=(WebView)v.findViewById(R.id.webView);
        WebSettings webSettings = web_view_load_data.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web_view_load_data.loadData(html, MIME_TYPE, null);
    }
}
