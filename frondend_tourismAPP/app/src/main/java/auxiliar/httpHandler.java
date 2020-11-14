package auxiliar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YURI VLADIMIR on 16/03/2017.
 */
public class httpHandler {

    String [] datos=null;
    public static Map<String, Integer> getCarrito() {
        return carrito;
    }

    public static void setCarrito(Map<String, Integer> carrito) {
        httpHandler.carrito = carrito;
    }

    private static Map<String,Integer> carrito;

    public String post(String posturl){
        try {
            String [] datos=RecuperarDatosMap(carrito).split("/");
            HttpClient httpclient = new DefaultHttpClient();
/*Creamos el objeto de HttpClient que nos permitira conectarnos mediante peticiones http*/
            HttpPost httppost = new HttpPost(posturl);
/*El objeto HttpPost permite que enviemos una peticion de tipo POST a una URL especificada*/
            //AÑADIR PARAMETROS
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Paxs","4"));
            params.add(new BasicNameValuePair("Outward_journey","02-04-2017"));
            params.add(new BasicNameValuePair("Return_journey","04-04-2017"));
            params.add(new BasicNameValuePair("Cod_package","P-01"));
           // params.add(new BasicNameValuePair("Paquetes",datos[0]));
            //params.add(new BasicNameValuePair("Integrantes",datos[1]));
        /*Una vez añadidos los parametros actualizamos la entidad de httppost, esto quiere decir en pocas palabras anexamos los parametros al objeto para que al enviarse al servidor envien los datos que hemos añadido*/

            httppost.setEntity(new UrlEncodedFormEntity(params));
                  /*Finalmente ejecutamos enviando la info al server*/
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();/*y obtenemos una respuesta*/
            String text = EntityUtils.toString(ent);
            return text;
        }
        catch(Exception e) { return "error";}

    }

    public String RecuperarDatosMap(Map<String,Integer> cart)
    {
        String codigo="";
        String integrantes="";
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            codigo=codigo+entry.getKey()+"|";
            integrantes=integrantes+String.valueOf(entry.getValue())+"|";
        }

        return codigo.substring(0,codigo.length()-1)+"/"+integrantes.substring(0,integrantes.length()-1);
    }

}
