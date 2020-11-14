/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Negocio;
import com.sw.Datos.InteraccionBD;
import com.sw.Objeto.Paquete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author YURI VLADIMIR
 */
public class NBusquedaPaquete {
      static String sql = "";
    static Connection cn = null;
    static PreparedStatement pst = null;

    public ArrayList<Paquete> ListarPaquete(String palabra, int idioma) throws SQLException {
        ArrayList<Paquete> listaDePaquetes = new ArrayList<Paquete>();
        InteraccionBD bd = new InteraccionBD();
        sql = "select cpaquetecod as cod,\n"
                + "                ctituloidioma1 as nombre1,\n"
                + "                ctituloidioma2 as nombre2,\n"
                + "                ctituloidioma3 as nombre3,\n"
                + "                ctituloidioma4 as nombre4,\n"
                + "                ctituloidioma5 as nombre5,\n"
                + "                ndias as dia,\n"
                + "                nnoches as noche,\n"
                + "                npreciouno as precio1,\n"
                + "                npreciodos as precio2,\n"
                + "                npreciotres as precio3,\n"
                + "                npreciocuatro as precio4,\n"
                + "                npreciocinco as precio5,\n"
                + "                cfoto1 from tpaquete where bestado='TRUE' and "+Idioma(idioma)+" ILIKE '%" + palabra+ "%' order by cpaquetecod ASC;";
        ResultSet rs = bd.Listar(sql);
        while (rs.next()) {
            Paquete paquete = new Paquete();
            paquete.setId(rs.getString(1));
            //paquete.setDescripcion(ProcesarDescripcion(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            paquete.setNombre(ProcesarNombre(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            paquete.setDias(rs.getString(7));
            paquete.setNoches(rs.getString(8));
            paquete.setPrecio(ProcesarPrecio(rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getDouble(12), rs.getDouble(13)));
            paquete.setIdDrawable(rs.getString(14));
            listaDePaquetes.add(paquete);
        }
        rs.close();
        return listaDePaquetes;
    }

    public String[] ProcesarNombre(String n1, String n2, String n3, String n4, String n5) {
        //parametros de la clase paquete
       String[] nombre = new String[5];
        for (int i = 0; i < 5; i++) {
            nombre[0] = n1;
            nombre[1] = n2;
            nombre[2] = n3;
            nombre[3] = n4;
            nombre[4] = n5;
        }
        return nombre;
    }

    public String[] ProcesarDescripcion(String d1, String d2, String d3, String d4, String d5) {
        String[] descripcion = new String[5];
        descripcion[0] = d1;
        descripcion[1] = d1;
        descripcion[2] = d1;
        descripcion[3] = d1;
        descripcion[4] = d1;
        return descripcion;
    }

    public double[] ProcesarPrecio(double p1, double p2, double p3, double p4, double p5) {
        double[] precio = new double[5];
        precio[0] = p1;
        precio[1] = p2;
        precio[2] = p3;
        precio[3] = p4;
        precio[4] = p5;
        return precio;
    }

    public String[] ProcesarImagen(String i1, String i2, String i3, String i4, String i5) {
        String[] imagen = new String[5];
        imagen[0] = i1;
        imagen[1] = i2;
        imagen[2] = i3;
        imagen[3] = i4;
        imagen[4] = i5;
        return imagen;
    }
     public String Idioma(int idioma)
    {
        String idiom="";
        switch(idioma)
        {
            case 1:idiom="ctituloidioma1";
                break;
            case 2:idiom="ctituloidioma2";
                break;
            case 3:idiom="ctituloidioma3";
                break;
            default:idiom="ctituloidioma2";
        }
        return idiom;
    }
}
