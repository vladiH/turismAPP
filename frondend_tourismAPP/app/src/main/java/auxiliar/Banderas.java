package auxiliar;

/**
 * Created by YURI VLADIMIR on 10/03/2017.
 */
public class Banderas {
    public static boolean isBanderaInicio() {
        return BanderaInicio;
    }

    public static void setBanderaInicio(boolean banderaInicio) {
        BanderaInicio = banderaInicio;
    }

    private static boolean BanderaInicio=false;
}
