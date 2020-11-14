/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Objeto;



/**
 *
 * @author YURI VLADIMIR
 */
public class Paquete {

    public String[] getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }

    public double[] getPrecio() {
        return precio;
    }

    public void setPrecio(double[] precio) {
        this.precio = precio;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getNoches() {
        return noches;
    }

    public void setNoches(String noches) {
        this.noches = noches;
    }
    private String [] nombre;
    private double [] precio;
    private String idDrawable;
    private String id;
    private String  dias;
    private String  noches;
   }
