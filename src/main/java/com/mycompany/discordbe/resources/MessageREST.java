package com.mycompany.discordbe.resources;

import Models.Message;
import Services.Controllers.MessageCon;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("messages")
public class MessageREST {
    MessageCon mcon;

    @Path("getMessage/byChatID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChat(@PathParam("id") int chatID) {
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("getChat/bychatNum/{chatNum}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChat(@PathParam("chatNum") String chatNum) {
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("add/{chatID}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message,@PathParam("chatID") int chatID) {
        return (mcon.addMessage(message, chatID)) ?
                Response.status(Response.Status.CREATED).build() :
                Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    @Path("delete/{messageID}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMessage(@PathParam("messageID") int messageID) {
        return (mcon.deleteMessage(messageID)) ?
                Response.status(Response.Status.CREATED).build() :
                Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    
}