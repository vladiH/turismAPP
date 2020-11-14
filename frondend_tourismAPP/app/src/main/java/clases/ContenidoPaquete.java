package clases;

/**
 * Created by YURI VLADIMIR on 14/03/2017.
 */
public class ContenidoPaquete {
    public  ContenidoPaquete() {

    }

    public ContenidoPaquete(String id, String descripcion, String itinerario, String [] idDrawable, String [] nombre)
    {
        this.id=id;
        this.descripcion=descripcion;
        this.itinerario=itinerario;
        this.idDrawable=idDrawable;
        this.nombre=nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public String[] getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String[] idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String[] getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }
    public static final ContenidoPaquete [] c=new ContenidoPaquete [1];
    private String id;
    private String descripcion;
    private String itinerario;
    private String [] idDrawable;
    private String [] nombre;
}
