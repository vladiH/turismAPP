/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Negocio;

import com.sw.Datos.InteraccionBD;
import com.sw.Objeto.ContenidoPaquete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YURI VLADIMIR
 */
public class NContenidoPaquete {
      //parametros de la base de datos
    private static String descripcion="";
    private static String itinerario="";
    private  List<String> imagen=new ArrayList<String>();
    private List<String> destino=new ArrayList<String>();
    
    static String sql = "";
    static Connection cn = null;
    static PreparedStatement pst = null;
    public ContenidoPaquete [] ListarCPaquete(String codigo, int idioma) throws SQLException {
        
        ContenidoPaquete []contenidoPaquete = new ContenidoPaquete[1];
        InteraccionBD bd = new InteraccionBD();
        sql = "select "+Idioma(idioma)+","+IdiomaI(idioma)+", cfoto1, cfoto2 ,cfoto3 ,cfoto4 ,cfoto5 from tpaquete where cpaquetecod='"+codigo+"'";
        ResultSet rs = bd.Listar(sql);
        while (rs.next()) {
            descripcion=rs.getString(1);
            itinerario=rs.getString(2);
            ImagenPaquete(rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        }
        sql = "select d.cdestino,\n" +
                "       d.cimagen\n" +
                "       from tdestino d \n" +
                "                inner join tpaquetedestino pd\n" +
                "       on (d.ndestinocod=pd.ndestinocod and d.bestado='true' and pd.cpaquetecod='"+codigo+"')";
         rs = bd.Listar(sql);
         while (rs.next()) {
              if(ProcesarImagenDestino(rs.getString(2)))
              {
                  ProcesarDestino(rs.getString(1));
              }
        }
         rs.close();
         ContenidoPaquete contenido=new ContenidoPaquete();
         contenido.setId(codigo);
         contenido.setDescripcion(descripcion);
         contenido.setItinerario(itinerario);
         contenido.setNombreDestino(destino);
         contenido.setIdDrawable(imagen);
         contenidoPaquete[0]=contenido;
         
        return contenidoPaquete;
    }
    
    public String Idioma(int idioma)
    {
        String idiom="";
        switch(idioma)
        {
            case 1:idiom="cdescripcionidioma1";
                break;
            case 2:idiom="cdescripcionidioma2";
                break;
            case 3:idiom="cdescripcionidioma3";
                break;
            default:idiom="cdescripcionidioma2";
        }
        return idiom;
    }
    public void ImagenPaquete(String i1, String i2, String i3, String i4, String i5)
    {
        if(ProcesarImagenTour(i1))
        {
            ProcesarDestino("Tour galería - 1");
        }
        if(ProcesarImagenTour(i2))
        {
            ProcesarDestino("Tour galería - 2");
        }
        if(ProcesarImagenTour(i3))
        {
            ProcesarDestino("Tour galería - 3");
        }
        if(ProcesarImagenTour(i4))
        {
            ProcesarDestino("Tour galería - 4");
        }
        if(ProcesarImagenTour(i5))
        {
            ProcesarDestino("Tour galería - 5");
        }
    }
    public String IdiomaI(int idioma)
    {
        String idiom="";
        switch(idioma)
        {
            case 1:idiom="citinerarioidioma1";
                break;
            case 2:idiom="citinerarioidioma2";
                break;
            case 3:idiom="citinerarioidioma3";
                break;
            default:idiom="citinerarioidioma2";
        }
        return idiom;
    }
    
     public boolean ProcesarImagenTour(String i1) {
         if(!ParserRuta(i1).equals("tourxdefecto.png"))
         {
              imagen.add(i1);
              return true;
         }
         return false;
    }
     public boolean ProcesarImagenDestino(String i1) {
         if(!ParserRuta(i1).equals("destinoxdefecto.png"))
         {
              imagen.add(i1);
              return true;
         }
         return false;
    }
      public void ProcesarDestino(String i1) {
      destino.add(i1);
    }
      
      public String ParserRuta(String ruta)
      {
          String [] rut=ruta.split("/");
          return rut[rut.length-1];
      }
}
