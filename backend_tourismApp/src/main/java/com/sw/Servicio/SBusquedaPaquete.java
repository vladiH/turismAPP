/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Servicio;


import com.sw.Clases.CPaquete;
import com.sw.Negocio.NBusquedaPaquete;
import com.sw.Objeto.Paquete;
import java.util.ArrayList;
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
public class SBusquedaPaquete {
      @GET
      @Path("/footpathperu/producto/listar/{palabra}/{idioma}")
      @Produces("application/json")
      public CPaquete listarTodosPaquetesBuscadosJSON(@PathParam("palabra") String Palabra, @PathParam("idioma")  int Idioma) throws Exception {
            NBusquedaPaquete paqueteBusqueda = new NBusquedaPaquete();
            ArrayList<Paquete> listaDeProductos = paqueteBusqueda.ListarPaquete(Palabra,Idioma);
            return new CPaquete(listaDeProductos);
             }
}
