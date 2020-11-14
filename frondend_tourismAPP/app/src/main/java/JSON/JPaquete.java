package JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import auxiliar.AuxiliarHelper;
import clases.PaqueteHelper;
import clases.ParcelPaquete;


/**
 * Created by YURI VLADIMIR on 25/02/2017.
 */
public class JPaquete {
    //llamamos a  la clase Paquete de clases
    public static final String JSON_ARRAY = "items";
    public static final String KEY_ID = "id";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DIAS = "dias";
    public static final String KEY_NOCHES= "noches";
    public static final String KEY_PRECIO = "precio";
    public static final String KEY_DRAWABLE= "idDrawable";

    private JSONArray paq = null;

    //parametros del objeto json
    private String [] Nombre= null;
    private double [] Precio=null;

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
                    String[] NOMBRE = ProcesarNombre((JSONArray) items1.get(KEY_NOMBRE));
                    String DIA = (String) items1.get(KEY_DIAS);
                    String NOCHE = (String) items1.get(KEY_NOCHES);
                    double[] PRECIO = ProcesarPrecio((JSONArray) items1.get(KEY_PRECIO));
                    String DRAWABLE = "https://www.e-ranti.com/pricing_fpp/" + AuxiliarHelper.getAuxiliar().decodeUTF8(AuxiliarHelper.getAuxiliar().encodeUTF8((String) items1.get(KEY_DRAWABLE)));
                    PaqueteHelper.getPaquete().PAQUETES.add(new ParcelPaquete(ID, NOMBRE, DIA, NOCHE, PRECIO, DRAWABLE));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }
    public double [] ProcesarPrecio(JSONArray precio)
    {
        Precio=new double[precio.size()];
        for(int i=0;i<precio.size();i++)
        {
            Precio[i]=Double.valueOf(precio.get(i).toString());
        }
        return Precio;
    }
    public String[] ProcesarNombre(JSONArray nombre)
    {
         Nombre=new String[nombre.size()];
            for(int j=0;j<nombre.size();j++)
            {
                Nombre[j]= AuxiliarHelper.getAuxiliar().decodeUTF8(AuxiliarHelper.getAuxiliar().encodeUTF8(nombre.get(j).toString()));
            }
        return Nombre;
    }
}
