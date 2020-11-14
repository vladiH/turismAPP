/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.servicioweb;

/**
 *
 * @author YURI VLADIMIR
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
 
@Path("/")
public class HelloService {
 
    @GET
    @Path("/Hello/{name}")
    public Response printMessage(@PathParam("name") String name) {
        return Response.status(200).entity("Hello " + name).build();
    }
}
