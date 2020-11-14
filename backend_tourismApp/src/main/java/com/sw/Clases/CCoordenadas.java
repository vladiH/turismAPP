/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Clases;
import com.sw.Objeto.Coordenadas;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author YURI VLADIMIR
 */
@XmlRootElement(name = "Lista")
public class CCoordenadas {
      private Coordenadas [] LatLon;

    public CCoordenadas() {
    }

    public CCoordenadas(Coordenadas [] LatLon) {
        this.LatLon= LatLon;
    }

    @XmlElement(name = "coordenadas")
    public Coordenadas [] getItems() {
        return LatLon;
    }
}
