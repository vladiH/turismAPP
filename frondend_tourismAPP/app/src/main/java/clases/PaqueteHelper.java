package clases;

/**
 * Created by YURI VLADIMIR on 20/03/2017.
 */
public class PaqueteHelper {
    private static Paquete b=new Paquete();
    public static Paquete getPaquete() {
        if (b == null) {
            b = new Paquete();
        }
        return b;
    }
}
