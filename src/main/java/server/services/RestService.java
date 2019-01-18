package server.services;

import com.google.gson.Gson;
import server.handler.PersistenceHandler;
import server.models.EventCreationModel;
import server.models.EventPropertyModel;
import server.reply.Reply;
import server.reply.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
public class RestService {

    //TODO: Unit tests with and without auth

    private PersistenceHandler persistenceHandler = new PersistenceHandler();

    //ADD REST FUNCTIONALITY HERE

    @GET
    @Path("/getaccounts")
    public Response getAccounts() {
        Reply reply = new Reply(Status.OK, persistenceHandler.getAccounts());
        return responseBuilder(reply);
    }

    @POST
    @Path("/newaccount")
    public Response newAccount(@QueryParam("mail") String email, @QueryParam("pw") String password) {
        Reply reply;
        Boolean success = persistenceHandler.newAccount(email, password);
        if (success) {
            reply = new Reply(Status.OK, true);
        } else reply = new Reply(Status.ERROR, false);
        return responseBuilder(reply);

    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("mail") String email, @QueryParam("pw") String password) {
        Reply reply;
        String token = persistenceHandler.login(email, password);
        if (token == null) reply = new Reply(Status.ERROR, false);
        else reply = new Reply(Status.OK, token);
        return responseBuilder(reply);
    }

    @GET
    @Path("/verifytoken")
    public Response verifyToken(@QueryParam("token") String token) {
        Reply reply = new Reply(Status.OK, persistenceHandler.verifyToken(token));
        return responseBuilder(reply);
    }

    @POST
    @Path("/newtimeline")
    public Response newTimeline(@QueryParam("token") String token, @QueryParam("name") String name) {
        Reply reply;
        String email = confirmLoggedIn(token);
        if (email == null) return responseBuilder(200, "Invalid session.");
        boolean success = persistenceHandler.newTimeline(email, name);
        if (success) reply = new Reply(Status.OK, true);
        else reply = new Reply(Status.ERROR, "Timeline already exists, or an error occurred.");
        return responseBuilder(reply);
    }

    private String confirmLoggedIn(String token) {
        return persistenceHandler.verifyToken(token);
    }

    private Response responseBuilder(int code, String message) {
        return Response.status(code).entity(message).build();
    }

    private Response responseBuilder(Reply reply) {
        return responseBuilder(reply.getStatus().getCode(), reply.getMessage());
    }

    @GET
    @Path("/timelines")
    public Response timelines(@QueryParam("token") String token) {
        List l = persistenceHandler.getTimelines(persistenceHandler.verifyToken(token));
        Reply reply = new Reply(Status.OK, l);
        return responseBuilder(reply);
    }

    @POST
    @Consumes("application/json")
    @Path("/event")
    public Response event(@QueryParam("token") String token, String data) {
        Reply reply;
        String email = confirmLoggedIn(token);
        if (email == null) return responseBuilder(200, "Invalid session.");

        if (persistenceHandler.event(data)) reply = new Reply(Status.OK, true);
        else reply = new Reply(Status.ERROR, false);
        return responseBuilder(reply);
    }

    @POST
    @Path("/deleteEvent")
    public Response deleteEvent(@QueryParam("token") String token, @QueryParam("id") int id) {
        Reply reply;
        String email = confirmLoggedIn(token);
        if (email == null) return responseBuilder(200, "Invalid session.");

        if (persistenceHandler.deleteEvent(id)) reply = new Reply(Status.OK, true);
        else reply = new Reply(Status.ERROR, false);
        return responseBuilder(reply);
    }

    @GET
    @Path("/eventshell")
    public Response eventShell() {
        Gson gson = new Gson();

        EventCreationModel eventCreationModel = new EventCreationModel("name", "01/01/2000", 1);
        eventCreationModel.setEventId(1);
        eventCreationModel.addProperty(new EventPropertyModel(1, "generic description", "property name", 1));
        Reply reply = new Reply(Status.OK, gson.toJson(eventCreationModel));
        return responseBuilder(reply);
    }

    @GET
    @Path("/getEvents")
    public Response getEvents(@QueryParam("token") String token, @QueryParam("id") int timelineId) {
        Reply reply;
        String email = confirmLoggedIn(token);
        if (email == null) return responseBuilder(200, "Invalid session.");

        reply = new Reply(Status.OK, persistenceHandler.getEvents(timelineId));
        return responseBuilder(reply);
    }
}