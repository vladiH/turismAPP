/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Clases;

import com.sw.Objeto.ContenidoPaquete;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YURI VLADIMIR
 */
@XmlRootElement(name = "Lista")
public class CContenidoPaquete {

    private ContenidoPaquete [] CPAQUETES;

    public CContenidoPaquete() {
    }

    public CContenidoPaquete(ContenidoPaquete [] CPAQUETES) {
        this.CPAQUETES = CPAQUETES;
    }

    @XmlElement(name = "cpaquetes")
    public ContenidoPaquete [] getItems() {
        return CPAQUETES;
    }
}