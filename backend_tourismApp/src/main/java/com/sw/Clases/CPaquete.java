/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Clases;

import com.sw.Objeto.Paquete;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YURI VLADIMIR
 */
@XmlRootElement(name = "Lista")
public class CPaquete {

    private List<Paquete> PAQUETES;

    public CPaquete() {
    }

    public CPaquete(List<Paquete> PAQUETES) {
        this.PAQUETES = PAQUETES;
    }

    @XmlElement(name = "paquetes")
    public List<Paquete> getItems() {
        return PAQUETES;
    }
}
