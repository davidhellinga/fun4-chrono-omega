package stubs;

import models.Account;
import models.Timeline;
import server.handler.IPersistenceHandler;
import server.models.EventCreationModel;

import java.util.List;

public class PersistenceHandlerStub implements IPersistenceHandler {

    RepositoryStub repo=new RepositoryStub();

    @Override
    public List<Account> getAccounts() {
        return null;
    }

    @Override
    public boolean newAccount(String email, String password) {
        return false;
    }

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public String verifyToken(String token) {
        return null;
    }

    @Override
    public List getTimelines(String email) {
        return null;
    }

    @Override
    public boolean newTimeline(String email, String name) {
        return false;
    }

    @Override
    public Timeline getTimelineById(int id) {
        return repo.getTimeline();
    }

    @Override
    public boolean event(String eventData) {
        return false;
    }

    @Override
    public boolean deleteEvent(int id) {
        return false;
    }

    @Override
    public List<EventCreationModel> getEvents(int timelineId) {
        return null;
    }
}