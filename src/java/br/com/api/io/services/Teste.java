package br.com.api.io.services;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("")
public class Teste implements Serializable {

    private static final long serialVersionUID = -7282457861406156040L;

    @Context
    private HttpServletRequest httpServletRequest;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/teste")
    public String teste() {
        
        return "true";
    }

}
