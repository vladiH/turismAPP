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
public class ContenidoPaquete {

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
  public List<String> getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(List<String> idDrawable) {
        this.idDrawable = idDrawable;
    }

    public List<String> getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(List<String> nombreDestino) {
        this.nombreDestino = nombreDestino;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String itinerario;
    private String descripcion;
    private List<String> idDrawable;
    private List<String> nombreDestino;
    private String id;
}
