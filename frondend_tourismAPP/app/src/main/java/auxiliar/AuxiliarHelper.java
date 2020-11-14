package auxiliar;

/**
 * Created by YURI VLADIMIR on 13/03/2017.
 */
public class AuxiliarHelper {
    private static Auxiliar a=new Auxiliar();
    public static Auxiliar getAuxiliar() {
        if (a == null) {
            a = new Auxiliar();
        }
        return a;
    }
}
