package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURI VLADIMIR on 27/03/2017.
 */
public class Menu {
    private String id;
    private String imagen_menu_fondo;
    private String icono_menu;
    private String nombre_menu;
    private static List<Menu> menus=new ArrayList<Menu>();

    public Menu()
    {

    }
    public Menu(String imagen_menu_fondo, String icono_menu, String nombre_menu)
    {
        this.imagen_menu_fondo=imagen_menu_fondo;
        this.icono_menu=icono_menu;
        this.nombre_menu=nombre_menu;
    }
    public String getImagen_menu_fondo() {
        return imagen_menu_fondo;
    }

    public void setImagen_menu_fondo(String imagen_menu_fondo) {
        this.imagen_menu_fondo = imagen_menu_fondo;
    }

    public String getIcono_menu() {
        return icono_menu;
    }

    public void setIcono_menu(String icono_menu) {
        this.icono_menu = icono_menu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Menu> getMenus() {
        return menus;
    }

    public static void setMenus(List<Menu> menus) {
        Menu.menus = menus;
    }
}
