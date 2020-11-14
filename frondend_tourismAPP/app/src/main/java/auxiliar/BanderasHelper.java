package auxiliar;

/**
 * Created by YURI VLADIMIR on 10/03/2017.
 */
public class BanderasHelper {
    private static Banderas b=new Banderas();
    public static Banderas getBandera() {
        if (b == null) {
            b = new Banderas();
        }
        return b;
    }
}
