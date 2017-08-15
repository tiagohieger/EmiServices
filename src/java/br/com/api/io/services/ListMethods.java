package br.com.api.io.services;

import br.com.entitys.Alert;
import br.com.entitys.Client;
import br.com.entitys.CompanyComment;
import br.com.entitys.Evaluation;
import br.com.entitys.Indication;
import br.com.entitys.IndicationComment;
import br.com.entitys.Ticket;
import br.com.entitys.User;
import br.com.filters.AlertFilter;
import br.com.filters.ClientFilter;
import br.com.filters.CompanyCommentFilter;
import br.com.filters.EvaluationFilter;
import br.com.filters.IndicationCommentFilter;
import br.com.filters.IndicationFilter;
import br.com.filters.TicketFilter;
import br.com.filters.UserFilter;
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
public class ListMethods implements Serializable {

    private static final long serialVersionUID = -7282457861406156040L;

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_alert")
    public String listAlert(@FormParam("filter") final String json) throws Throwable {

        final AlertFilter filter = Converter.jsonToObject(json, AlertFilter.class);

        final AlertRn rn = GenRn.newInstance(Alert.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_client")
    public String listClient(@FormParam("filter") final String json) throws Throwable {

        final ClientFilter filter = Converter.jsonToObject(json, ClientFilter.class);

        final ClientRn rn = GenRn.newInstance(Client.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_company_comment")
    public String listCompanyComment(@FormParam("filter") final String json) throws Throwable {

        final CompanyCommentFilter filter = Converter.jsonToObject(json, CompanyCommentFilter.class);

        final CompanyCommentRn rn = GenRn.newInstance(CompanyComment.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_evaluation")
    public String listEvaluation(@FormParam("filter") final String json) throws Throwable {

        final EvaluationFilter filter = Converter.jsonToObject(json, EvaluationFilter.class);

        final EvaluationRn rn = GenRn.newInstance(Evaluation.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_indication_comment")
    public String listIndicationComment(@FormParam("filter") final String json) throws Throwable {

        final IndicationCommentFilter filter = Converter.jsonToObject(json, IndicationCommentFilter.class);

        final IndicationCommentRn rn = GenRn.newInstance(IndicationComment.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_indication")
    public String listIndication(@FormParam("filter") final String json) throws Throwable {

        final IndicationFilter filter = Converter.jsonToObject(json, IndicationFilter.class);

        final IndicationRn rn = GenRn.newInstance(Indication.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_ticket")
    public String listTicket(@FormParam("filter") final String json) throws Throwable {

        final TicketFilter filter = Converter.jsonToObject(json, TicketFilter.class);

        final TicketRn rn = GenRn.newInstance(Ticket.class);

        return Converter.objectToJson(rn.list(filter));
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/list_user")
    public String listUser(@FormParam("filter") final String json) throws Throwable {

        final UserFilter filter = Converter.jsonToObject(json, UserFilter.class);

        final UserRn rn = GenRn.newInstance(User.class);

        return Converter.objectToJson(rn.list(filter));
    }

}
