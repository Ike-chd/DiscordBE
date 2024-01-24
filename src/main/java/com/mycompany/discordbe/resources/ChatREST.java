package com.mycompany.discordbe.resources;

import Services.Controllers.ChatCon;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("chats")
public class ChatREST {
    private ChatCon ccon;
    
    @Path("getChat/byID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChat(@PathParam("id")int chatID){
        return Response.status(Response.Status.CREATED).build();
    }
    
    @Path("getChat/bychatNum/{chatNum}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChat(@PathParam("chatNum")String chatNum){
        return Response.status(Response.Status.CREATED).build();
    }
    
    @Path("getAllChats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllChats(@PathParam("chatNum")String chatNum){
        return Response.status(Response.Status.CREATED).build();
    }
}