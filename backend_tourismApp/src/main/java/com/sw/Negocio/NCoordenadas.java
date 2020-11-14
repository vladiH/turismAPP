/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Negocio;

import com.sw.Datos.InteraccionBD;
import com.sw.Objeto.Coordenadas;
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
public class NCoordenadas {
    //parametros de la base de datos

    static String sql = "";
    static Connection cn = null;
    static PreparedStatement pst = null;
    public List<String>Titulo=new ArrayList<String>();
    public List<Double> Latitud=new ArrayList<Double>();
    public List<Double> Altitud=new ArrayList<Double>();
    
    public Coordenadas [] ListarCoordenadas(String codigo) throws SQLException {
        Coordenadas []listaDeCoordenadas = new Coordenadas[1];
        InteraccionBD bd = new InteraccionBD();
        sql = "select \n" +
                " d.cdestino,\n" +
                " d.clatitud, \n" +
                " d.clongitud \n" +
                " from tdestino d \n" +
                " INNER JOIN tpaquetedestino pd\n" +
                " on (d.ndestinocod=pd.ndestinocod and d.bestado='true' and pd.cpaquetecod='"+codigo+"') order by pd.nordenitinerario asc;";
        ResultSet rs = bd.Listar(sql);
        while (rs.next()) {
            ProcesarTitulo(rs.getString(1));
            ProcesarLatitud(rs.getDouble(2));
            ProcesarLongitud(rs.getDouble(3));
        }
        if(!(Titulo.isEmpty() && Latitud.isEmpty() && Altitud.isEmpty()))
        {
            Coordenadas coord=new Coordenadas();
            coord.setTitulo(Titulo);
            coord.setLatitud(Latitud);
            coord.setAltitud(Altitud);
            listaDeCoordenadas[0]= coord;
        }
        rs.close();
        return listaDeCoordenadas;
    }

    public void ProcesarTitulo(String titulo)
    {
        Titulo.add(titulo);
    }
     public void ProcesarLatitud(Double lat)
    {
        Latitud.add(lat);
    }
      public void ProcesarLongitud(Double lon)
    {
        Altitud.add(lon);
    }

    
}
