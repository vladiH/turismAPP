/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Datos;

/**
 *
 * @author YURI VLADIMIR
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vladimir
 */
public class Conexion {
   /* private final static String bd = "DB_PRICING";
    private final static String login = "postgres";
    private final static String password = "";
    private final static String url = "jdbc:postgresql://localhost:5432/"+bd;*/
    private final static String bd = "DBPricing_FPP";
    private final static String login = "postgres";
    private final static String password = "LAnube2016@db";
    private final static String url = "jdbc:postgresql://45.56.120.138:5432/"+bd; 
    public Connection conexion() {
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, login, password);
            if (conn!=null){
                //System.out.println("Conectado a la base de datos ["+bd+"]");
            }
            return conn;
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
