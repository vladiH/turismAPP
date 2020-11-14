/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Objeto;

import java.util.List;

/**
 *
 * @author YURI VLADIMIR
 */
public class Coordenadas {

    public List<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(List<String> titulo) {
        this.titulo = titulo;
    }

    public List<Double> getLatitud() {
        return latitud;
    }

    public void setLatitud(List<Double> latitud) {
        this.latitud = latitud;
    }

    public List<Double> getAltitud() {
        return altitud;
    }

    public void setAltitud(List<Double> altitud) {
        this.altitud = altitud;
    }

    private List<String>titulo=null;
    private List<Double> latitud=null;
    private List<Double> altitud=null;
}
