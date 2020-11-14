package clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.tonyvu.sc.model.interfaceCart;

/**
 * Created by YURI VLADIMIR on 10/02/2017.
 */
public class ParcelPaquete implements interfaceCart, Parcelable {

    private int precioSeleccion=0;
    private String id;
    private String [] nombre;
    private String dias;
    private String noches;
    private double [] precio;
    private String idDrawable;

    public static final Parcelable.Creator<ParcelPaquete> CREATOR = new Parcelable.Creator<ParcelPaquete>() {

        @Override
        public ParcelPaquete createFromParcel(Parcel source) {
            return new ParcelPaquete(source);
        }

        @Override
        public ParcelPaquete[] newArray(int size) {
            return new ParcelPaquete[size];
        }

    };
    public ParcelPaquete( String id, String [] nombres,String dia, String noche,double [] precios ,String idDrawable) {
        setId(id);
        setNombre(nombres);
        setDias(dia);
        setNoches(noche);
        setPrecio(precios);
        setIdDrawable(idDrawable);
    }
    public ParcelPaquete(Parcel p) {
        setId(p.readString());
        setNombre(p.createStringArray());
        //List nombres= new ArrayList<>();
       // p.readList(nombres, List.class.getClassLoader());
        //setNombre(nombres);
        setDias(p.readString());
        setNoches(p.readString());
        setPrecio(p.createDoubleArray());
        setIdDrawable(p.readString());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeStringArray(nombre);
        parcel.writeString(dias);
        parcel.writeString(noches);
        parcel.writeDoubleArray(precio == null || precio.length == 0 ? new double[]{} : precio);
        parcel.writeString(idDrawable);

    }


    public String[]getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }
    public String getDias() {
        return dias;
    }

    public void setDias(String  dias) {
        this.dias = dias;
    }

    public String  getNoches() {
        return noches;
    }

    public void setNoches(String noches) {
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

    public void setPrecioSeleccion(int precioSeleccion)
    {
        this.precioSeleccion=precioSeleccion;
    }

    @Override
    public String getIdentificador() {
        return getId();
    }

    @Override
    public double [] getPrecios() {
        return getPrecio();
    }
}
