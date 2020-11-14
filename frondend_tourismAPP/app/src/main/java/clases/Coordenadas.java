package clases;

import java.util.List;

/**
 * Created by YURI VLADIMIR on 04/03/2017.
 */
public class Coordenadas {
    public Coordenadas() {}
    public Coordenadas(List<String> t, List<Double> lat, List<Double> alt)
    {
        titulo=t;
        latitud=lat;
        altitud=alt;
    }
    public List<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(List<String> titulo) {
        this.titulo = titulo;
    }

    public List<Double> getLatitud() {
        return latitud;
    }

    public void setLatitud(List<Double> latitud) {
        this.latitud = latitud;
    }

    public List<Double> getAltitud() {
        return altitud;
    }

    public void setAltitud(List<Double> altitud) {
        this.altitud = altitud;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }



    public static final Coordenadas [] c=new Coordenadas[1];
    /*) {
        List<String> Titulo=new ArrayList<>();
        Titulo.add("Cusco");
        List<Double> Latitud=new ArrayList<>();
        Latitud.add(-13.516731920901007);
        List<Double> Altitud=new ArrayList<>();
        Altitud.add(-71.9788384437561);
        setLatitud(Latitud);
        setAltitud(Altitud);
        setTitulo(Titulo);
    }*/
    private List<String>titulo;
    private List<Double> latitud;
    private List<Double> altitud;
    private String Codigo="";

}
