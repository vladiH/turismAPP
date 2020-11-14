package JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import auxiliar.AuxiliarHelper;
import clases.ContenidoPaquete;

/**
 * Created by YURI VLADIMIR on 14/03/2017.
 */
public class JContenidoPaquete {

    ContenidoPaquete contenido=new ContenidoPaquete();
    public static final String JSON_ARRAY = "items";
    public static final String KEY_ID = "id";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_NOMBRE = "nombreDestino";
    public static final String KEY_ITINERARIO = "itinerario";
    public static final String KEY_DRAWABLE= "idDrawable";


    private JSONArray paq = null;

    //parametros del objeto json
    private String[] NombreDestino=null;
    private String [] IdDrawable=null;

    public boolean parseJPaquete(String json){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(json);

            JSONObject jsonObject = (JSONObject) obj;
            paq=(JSONArray) jsonObject.get(JSON_ARRAY);
            for(int i=0;i<paq.size();i++)
            {
                Object  items = paq.get(i);
                if(items!=null) {
                    JSONObject items1 = (JSONObject) items;
                    String ID = (String) items1.get(KEY_ID);
                    //String [] DESCRIPCION=ProcesarDescripcion((JSONArray) items1.get(KEY_DESCRIPCION));
                    String[] NOMBRE = ProcesarNombre((JSONArray) items1.get(KEY_NOMBRE));
                    String[] DRAWABLE = ProcesarImagen((JSONArray) items1.get(KEY_DRAWABLE));
                    String DESCRIPCION = ConvertirHTML((String) items1.get(KEY_DESCRIPCION));
                    String ITINERARIO = ConvertirHTML((String) items1.get(KEY_ITINERARIO));

                    //String DRAWABLE="https://www.e-ranti.com/pricing_fpp/"+(String) items1.get(KEY_DRAWABLE);
                    contenido.c[0] = new ContenidoPaquete(ID, DESCRIPCION, ITINERARIO, DRAWABLE, NOMBRE);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }
    public String[] ProcesarNombre(JSONArray nombre)
    {
        NombreDestino=new String[nombre.size()];
        for(int j=0;j<nombre.size();j++)
        {
            NombreDestino[j]= AuxiliarHelper.getAuxiliar().decodeUTF8(AuxiliarHelper.getAuxiliar().encodeUTF8(nombre.get(j).toString()));
        }
        return NombreDestino;
    }
    public String [] ProcesarImagen(JSONArray imagen)
    {
        IdDrawable=new String[imagen.size()];
        for(int i=0;i<imagen.size();i++)
        {
            IdDrawable[i]="https://www.e-ranti.com/pricing_fpp/"+imagen.get(i).toString();
        }
        return IdDrawable;
    }
    private static String ConvertirHTML(String s) {
        return "<html><body>"+ s + "</body></html>";
    }
}
