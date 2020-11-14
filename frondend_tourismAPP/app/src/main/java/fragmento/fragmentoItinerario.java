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

public class fragmentoItinerario extends Fragment {

    private ContenidoPaquete[]cpaquete;
    public void Producto(ContenidoPaquete [] cpaquete)
    {
        this.cpaquete=cpaquete;
    }

    public fragmentoItinerario() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_itinerario, container, false);
        cargarHtmlCodigo(view);
        return view;
    }
    public void cargarHtmlCodigo(View v)
    {
        // 0 nos indica el idioma
        String html=cpaquete[0].getItinerario();
        final String MIME_TYPE = "text/html";
        WebView web_view_load_data=(WebView)v.findViewById(R.id.webView);
        WebSettings webSettings = web_view_load_data.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web_view_load_data.loadData(html, MIME_TYPE, null);
    }
}
