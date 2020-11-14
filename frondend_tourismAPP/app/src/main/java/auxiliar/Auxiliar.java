package auxiliar;

import java.nio.charset.Charset;

/**
 * Created by YURI VLADIMIR on 06/02/2017.
 */
public class Auxiliar {
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private final Charset UTF8_CHARSET1 = Charset.forName("ISO_8859_1");
    private static int Idioma;
    private static int Paginas=4;
    private static int Contador=4;
    private static boolean modificado=false;
    public static int getIdioma() {
        return Idioma;
    }

    public static void setIdioma(String pIdioma) {
        switch (pIdioma){
            case "es":Idioma=0;
                break;
            case "in":Idioma=1;
                break;
            case "pt":Idioma=2;
                break;
        }
    }

    public static int getPaginas() {
        return Paginas;
    }

    public static void setPaginas(int paginas) {
        Paginas = paginas;
    }

    public static int getContador() {
        return Contador;
    }

    public static void setContador(int contador) {
        Contador = contador;
    }
    public static boolean isModificado() {
        return modificado;
    }

    public static void setModificado(boolean modificado) {
        Auxiliar.modificado = modificado;
    }

    public  String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    public byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET1);
    }

    public  String decodeUTF81(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET1);
    }

    public byte[] encodeUTF81(String string) {
        return string.getBytes(UTF8_CHARSET);
    }
}
