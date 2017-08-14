package br.com.api.io.services;

import br.com.entitys.User;
import br.com.filters.EntityFilter;
import br.com.filters.UserFilter;
import br.com.rn.UserRn;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        UserRn rn = new UserRn();
        UserFilter filter = new UserFilter();
        filter.setDirection(EntityFilter.Direction.ASC);
        try {
            List<User> list = rn.list(filter);
            System.out.println(list.size());
        } catch (Throwable ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "true";
    }

}
