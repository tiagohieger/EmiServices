package br.com.api.io.services;

import br.com.entitys.Alert;
import br.com.entitys.Client;
import br.com.entitys.CompanyComment;
import br.com.entitys.Evaluation;
import br.com.entitys.Indication;
import br.com.entitys.IndicationComment;
import br.com.entitys.Ticket;
import br.com.entitys.User;
import br.com.json.Converter;
import br.com.rn.AlertRn;
import br.com.rn.ClientRn;
import br.com.rn.CompanyCommentRn;
import br.com.rn.EvaluationRn;
import br.com.rn.GenRn;
import br.com.rn.IndicationCommentRn;
import br.com.rn.IndicationRn;
import br.com.rn.TicketRn;
import br.com.rn.UserRn;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("")
public class SaveMethods implements Serializable {

    private static final long serialVersionUID = -5040870887383745995L;

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_alert")
    public String saveAlert(@FormParam("entity") final String json) throws Throwable {

        final Alert entity = Converter.jsonToObject(json, Alert.class);

        final AlertRn rn = GenRn.newInstance(Alert.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_client")
    public String saveClient(@FormParam("entity") final String json) throws Throwable {

        final Client entity = Converter.jsonToObject(json, Client.class);

        final ClientRn rn = GenRn.newInstance(Client.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_company_comment")
    public String saveCompanyComment(@FormParam("entity") final String json) throws Throwable {

        final CompanyComment entity = Converter.jsonToObject(json, CompanyComment.class);

        final CompanyCommentRn rn = GenRn.newInstance(CompanyComment.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_evaluation")
    public String saveEvaluation(@FormParam("entity") final String json) throws Throwable {

        final Evaluation entity = Converter.jsonToObject(json, Evaluation.class);

        final EvaluationRn rn = GenRn.newInstance(Evaluation.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_indication_comment")
    public String saveIndicationComment(@FormParam("entity") final String json) throws Throwable {

        final IndicationComment entity = Converter.jsonToObject(json, IndicationComment.class);

        final IndicationCommentRn rn = GenRn.newInstance(IndicationComment.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_indication")
    public String saveIndication(@FormParam("entity") final String json) throws Throwable {

        final Indication entity = Converter.jsonToObject(json, Indication.class);

        final IndicationRn rn = GenRn.newInstance(Indication.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_ticket")
    public String saveTicket(@FormParam("entity") final String json) throws Throwable {

        final Ticket entity = Converter.jsonToObject(json, Ticket.class);

        final TicketRn rn = GenRn.newInstance(Ticket.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/save_user")
    public String saveUser(@FormParam("entity") final String json) throws Throwable {

        final User entity = Converter.jsonToObject(json, User.class);

        final UserRn rn = GenRn.newInstance(User.class);

        rn.save(entity);

        return Converter.objectToJson(entity);
    }

}
