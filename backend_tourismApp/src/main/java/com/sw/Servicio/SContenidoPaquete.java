/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Servicio;

import com.sw.Clases.CContenidoPaquete;
import com.sw.Negocio.NContenidoPaquete;
import com.sw.Objeto.ContenidoPaquete;
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
public class SContenidoPaquete {
      @GET
      @Path("/footpathperu/coordenadas/listar/{codigo}/{idioma}")
      @Produces("application/json")
     public CContenidoPaquete listarContenidoPaqueteJSON(@PathParam("codigo") String Codigo, @PathParam("idioma")  int Idioma) throws Exception {
            NContenidoPaquete coordenadasControl = new NContenidoPaquete();
            ContenidoPaquete [] listaDeProductos = coordenadasControl.ListarCPaquete(Codigo,Idioma);
            return new CContenidoPaquete(listaDeProductos);
             
        }
}
