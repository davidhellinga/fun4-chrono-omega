package server.services;

import com.google.gson.Gson;
import server.handler.PersistenceHandler;
import server.reply.Reply;
import server.reply.Status;
import server.responseModels.SubmitResponse;
import server.responseModels.SubmitResultResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/api")
    public class RestService {

    PersistenceHandler persistenceHandler = new PersistenceHandler();

    //ADD REST FUNCTIONALITY HERE

    @GET
    @Path("/getaccounts")
    public Response findLists() {
        Reply reply = new Reply(Status.OK, persistenceHandler.GetAccounts());
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }


}

/*
@POST
    @Consumes("application/json")
    @Path("/submit")
    public Response submit(String data) {
        Gson gson = new Gson();
        Reply reply = null;
        SubmitResponse submitResponse = gson.fromJson(data, SubmitResponse.class);
        persistenceHandler.SubmitEntry(submitResponse.getProblemWords(), submitResponse.getTranslationWords(), submitResponse.getTitle(), submitResponse.getProblemLanguage(), submitResponse.getTranslationLanguage(), submitResponse.getPersonEmail());
        reply = new Reply(Status.OK, true);
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @GET
    @Path("/getlists")
    public Response findLists() {
        Reply reply = new Reply(Status.OK, persistenceHandler.GetLists());
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/getlistbyid")
    public Response findListById(@DefaultValue("") @QueryParam("id") String id, @Context UriInfo uriInfo) {

        Reply reply = new Reply(Status.OK, persistenceHandler.GetListById(Integer.parseInt(id)));
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/getlistsbyemail")
    public Response findListsByEmail(@DefaultValue("") @QueryParam("email") String email, @Context UriInfo uriInfo) {

        Reply reply = new Reply(Status.OK, persistenceHandler.GetListsByEmail(email));
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/submitresult")
    public Response submitResult(String data) {
        Gson gson = new Gson();
        Reply reply = null;
        SubmitResultResponse submitResultResponse = gson.fromJson(data, SubmitResultResponse.class);
        persistenceHandler.SubmitResultEntry(submitResultResponse.getWordListId(), submitResultResponse.getScore(), submitResultResponse.getTotal(), submitResultResponse.getEmail());
        reply = new Reply(Status.OK, true);
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/getresultsbyemail")
    public Response findResultsByEmail(@DefaultValue("") @QueryParam("email") String email, @Context UriInfo uriInfo) {

        Reply reply = new Reply(Status.OK, persistenceHandler.GetResultsByEmail(email));
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
 */
