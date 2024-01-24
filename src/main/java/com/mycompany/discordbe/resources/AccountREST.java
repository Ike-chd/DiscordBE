package com.mycompany.discordbe.resources;

import Models.Account;
import Services.Controllers.AccountCon;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("accounts")
public class AccountREST {

    AccountCon acon;

    @Path("getAccount/byID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") int accountID) {
        return Response.ok(acon.getAccount(accountID)).status(Response.Status.CREATED).build();
    }

    @Path("getAccount/byUsername/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("username") String username) {
        return Response.ok(acon.getAccount(username)).status(Response.Status.CREATED).build();
    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Account account) {
        return (acon.verifyPassword(account))
                ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        return (acon.addAccount(account))
                ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}