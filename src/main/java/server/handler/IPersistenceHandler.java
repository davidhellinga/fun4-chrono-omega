package server.handler;

import models.Account;
import models.Timeline;
import server.models.EventCreationModel;

import java.util.List;

public interface IPersistenceHandler {
    List<Account> getAccounts();

    boolean newAccount(String email, String password);

    String login(String email, String password);

    String verifyToken(String token);

    List getTimelines(String email);

    boolean newTimeline(String email, String name);

    Timeline getTimelineById(int id);

    boolean event(String eventData);

    boolean deleteEvent(int id);

    List<EventCreationModel> getEvents(int timelineId);


}
