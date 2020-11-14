/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.Servicio;

/**
 *
 * @author YURI VLADIMIR
 */
import com.sw.Clases.CPaquete;
import com.sw.Negocio.NPaquete;
import com.sw.Objeto.Paquete;
import java.util.ArrayList;
import javax.ws.rs.GET;
 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/")
public class SPaquete {
       //--------------------LISTAR TODOS LOS PRODUCTO----------------------------------------------
        @GET
        @Path("/footpathperu/producto/listar/inicio/{paginas}/{nrover}")
        @Produces("application/json")
        public CPaquete listarTodosPaquetesXML(@PathParam("paginas") int pagMostrada,@PathParam("nrover") int pag) throws Exception {
            NPaquete paqueteControl = new NPaquete();
            ArrayList<Paquete> listaDeProductos = paqueteControl.ListarPaquete(pagMostrada,pag);
            return new CPaquete(listaDeProductos);
             
        }
}
