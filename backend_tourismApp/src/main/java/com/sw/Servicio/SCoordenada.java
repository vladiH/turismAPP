/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Servicio;

import com.sw.Clases.CCoordenadas;
import com.sw.Negocio.NCoordenadas;
import com.sw.Objeto.Coordenadas;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author YURI VLADIMIR
 */
@Path("/")
public class SCoordenada {
       @GET
       @Path("/footpathperu/coordenadas/listar/{codigo}")
       @Produces("application/json")
     public CCoordenadas listarCoordenadasJSON(@PathParam("codigo") String Codigo) throws Exception {
            NCoordenadas coordenadasControl = new NCoordenadas();
            Coordenadas [] listaDeProductos = coordenadasControl.ListarCoordenadas(Codigo);
            return new CCoordenadas(listaDeProductos);
             
        }
}
