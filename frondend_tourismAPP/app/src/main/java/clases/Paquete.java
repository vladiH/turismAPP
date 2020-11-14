package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURI VLADIMIR on 26/01/2017.
 */
public class Paquete {

    /*describcion 0=ingles 1=espanol 2=portugues, etc*
           se almacenara la descripcion del paquete con su respetivo idioma que se diferenciara
           en cada posicion del arreglo
            */


    /*
        nombre de paquetes almacenadas en un arreglo y cada posicion del arreglo es un
        nombre del mismo paquete pero en diferentes idiomas
        */
    private String []nombre;
    /*
    almacena el precio del paquete respectivo en dicha posicion,
    y dentro del arreglo se almacena los descuentos de dicho paquete
    */
    private double [] precio;

    /*
    almacena las imagenes de un paqute seleccionado
    */
    private String  idDrawable;

    /*
        identificador de cada paquete
        */
    private String id;


    //numero de dias
    private String  dias;
    //numero de noches
    private String  noches;

  /*  public Paquete(String id,String []descripcion, List<String []> nombre,String  dias,String  noches,double [] precio ,int [] idDrawable) {
        this.id=id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.dias=dias;
        this.noches=noches;
        this.precio=precio;
        this.idDrawable = idDrawable;
    }*/

    public static List<ParcelPaquete> PAQUETES = new ArrayList<ParcelPaquete>();

  static {
      /*  //descripcion
        String [] IDescripcion=new String[5];
        IDescripcion[0]=ConvertirHTML("<p><span style=\"color:#B22222\"><strong>Machupichu</strong></span><br />\n" +
                "<span style=\"color:rgb(139, 139, 139); font-family:open sans,sans-serif; font-size:14px\">?Es el mayor atractivo tur&iacute;stico del Per&uacute; y el m&aacute;s visitado, declarado Patrimonio de la Humanidad por la UNESCO. Cusco fue la ciudad y capital del Imperio Inca m&aacute;s grande, a continuaci&oacute;n, fue tomada por los conquistadores espa&ntilde;oles.</span></p>");
        IDescripcion[1]=ConvertirHTML("<p><span style=\"color:#B22222\"><strong>Machupichu</strong></span><br />\n" +
                "<span style=\"color:rgb(139, 139, 139); font-family:open sans,sans-serif; font-size:14px\">?It is the largest tourist attraction in Peru and the most visited, declared a World Heritage Site by UNESCO. Cusco was the largest city and capital of the Inca Empire, then was taken by the Spanish conquerors.</span></p>");
        IDescripcion[2]=ConvertirHTML("");
        IDescripcion[3]=ConvertirHTML("");
        IDescripcion[4]=ConvertirHTML("");

        //nombre
        List<String []> pnombre=new ArrayList<>();
        String [] INombre=new String[5];
        //espanol
        INombre[0]="Machu Picchu";
        INombre[1]="Andenes";
        INombre[2]="Escaleras";
        INombre[3]="Portada";
        INombre[4]="Escaleras";
        pnombre.add(INombre);
        //ingles
        INombre=new String[5];
        INombre[0]="Machu Picchu";
        INombre[1]="Andenes en ingles";
        INombre[2]="Escaleras en ingles";
        INombre[3]="Portada en ingles";
        INombre[4]="Escaleras en ingles";
        pnombre.add(INombre);

        //dias
        String  dia="8";
        //noche
        String  noche="6";

        //precio
        double [] IPrecio=new double[5];
        IPrecio[0]=80.0;
        IPrecio[1]=70.0;
        IPrecio[2]=60.0;
        IPrecio[3]=50.0;
        IPrecio[4]=40.0;

       //imagen

        int [] IImagen=new int[5];
        IImagen[0]= R.drawable.machupicchu;
        IImagen[1]=R.drawable.andenes_m;
        IImagen[2]=R.drawable.escaleras_m;
        IImagen[3]=R.drawable.pordada_m;
        IImagen[4]=R.drawable.escaleras_m;

        PAQUETES.add(new ParcelPaquete("0",IDescripcion,pnombre,dia,noche,IPrecio,IImagen));*/
   }

    public static List<ParcelPaquete> getPAQUETES() {
        return PAQUETES;
    }


    public String[] getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }

    public String  getDias() {
        return dias;
    }

    public void setDias(String  dias) {
        this.dias = dias;
    }

    public String  getNoches() {
        return noches;
    }

    public void setNoches(String  noches) {
        this.noches = noches;
    }

    public double[] getPrecio() {
        return precio;
    }

    public void setPrecio(double[] precio) {
        this.precio = precio;
    }
    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
