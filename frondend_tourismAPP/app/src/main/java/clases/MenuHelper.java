package clases;

/**
 * Created by YURI VLADIMIR on 27/03/2017.
 */
public class MenuHelper {
    private static Menu m=new Menu();
    public static Menu getMenu() {
        if (m == null) {
            m = new Menu();
        }
        return m;
    }
}
