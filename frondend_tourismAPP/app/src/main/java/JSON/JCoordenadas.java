package JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import clases.Coordenadas;

/**
 * Created by YURI VLADIMIR on 06/03/2017.
 */
public class JCoordenadas {
    public boolean bandera=false;
    //llamamos a  la clase Paquete de clases
    Coordenadas coordenadas=new Coordenadas();
    public static final String JSON_ARRAY = "items";
    public static final String KEY_TITULO= "titulo";
    public static final String KEY_LATITUD = "latitud";
    public static final String KEY_LONGITUD= "altitud";

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private final Charset UTF8_CHARSET1 = Charset.forName("ISO_8859_1");
    private JSONArray coord = null;

    //parametros del objeto json
    private List<String> Titulo=new ArrayList<>();
    private List<Double> Latitud=new ArrayList<>();
    private List<Double> Altitud=new ArrayList<>();


    public boolean parseJCoordenada(String json){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(json);

            JSONObject jsonObject = (JSONObject) obj;
            coord=(JSONArray) jsonObject.get(JSON_ARRAY);
            for(int i=0;i<coord.size();i++)
            {

                Object  items = coord.get(i);
                if(items!=null) {
                    bandera=true;
                    JSONObject items1 = (JSONObject) items;
                    List<String> TITULO = ProcesarTitulo((JSONArray) items1.get(KEY_TITULO));
                    List<Double> LATITUD = ProcesarLatitud((JSONArray) items1.get(KEY_LATITUD));
                    List<Double> LONGITUD = ProcesarLongitud((JSONArray) items1.get(KEY_LONGITUD));
                    coordenadas.c[0] = new Coordenadas(TITULO, LATITUD, LONGITUD);
                }
            }
          if(bandera==false)
          {
              List<String> Ti=new ArrayList<>();
              Ti.add("Cusco");
              List<Double> La=new ArrayList<>();
              La.add(-13.516731920901007);
              List<Double> Al=new ArrayList<>();
              Al.add(-71.9788384437561);
              coordenadas.c[0]=new Coordenadas(Ti,La,Al);
          }
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }
    public List<Double> ProcesarLatitud(JSONArray latitud)
    {
        for(int i=0;i<latitud.size();i++)
        {
            Latitud.add(Double.valueOf(latitud.get(i).toString()));
        }
        return Latitud;
    }
    public List <Double> ProcesarLongitud(JSONArray lonitud)
    {
        for(int i=0;i<lonitud.size();i++)
        {
           Altitud.add(Double.valueOf(lonitud.get(i).toString()));
        }
        return Altitud;
    }
    public List<String> ProcesarTitulo(JSONArray titulo)
    {
        for(int i=0;i<titulo.size();i++)
        {
            Titulo.add(decodeUTF8(encodeUTF8(titulo.get(i).toString())));
        }
        return Titulo;
    }
    String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET1);
    }
}
