package stubs;

import models.*;

import java.util.ArrayList;

public class RepositoryStub {
    private Account account = new Account();
    private Dateformat dateformat = new Dateformat();
    private Event event = new Event();
    private EventProperty eventProperty = new EventProperty();
    private Timeline timeline = new Timeline();
    private String unhashedPassword;

    public RepositoryStub() {
        account.setId(1);
        account.addTimeline(timeline);
        unhashedPassword = "123opdekie";
        account.setPassword("237716E67E22DAB2F0843D493BEFE2C46886E962BD14BB7F204C813DEF422B78");
        account.setEmail("test@oibss.com");

        dateformat.setId(1);
        dateformat.setFormat("mm/dd/yyyy");
        dateformat.setMonths(12);
        dateformat.setWeeks(4);
        dateformat.setDays(7);
        dateformat.setHours(24);

        timeline.setId(1);
        timeline.setDateformat(dateformat);
        timeline.setTitle("Anno Domini");
        timeline.setAccount(account);
        timeline.setEvents(new ArrayList<>());

        event.setId(1);
        event.setTimeline(timeline);
        event.setProperties(new ArrayList<>());
        event.setDate("21/03/1995");
        event.setName("Birth of the Lamb");
        timeline.addEvent(event);

        eventProperty.setId(1);
        eventProperty.setName("Weather");
        eventProperty.setDescription("A cold and rainy night, sharp winds blowing across the plains.");
        event.addProperty(eventProperty);
        eventProperty.setEvent(event);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Dateformat getDateformat() {
        return dateformat;
    }

    public void setDateformat(Dateformat dateformat) {
        this.dateformat = dateformat;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventProperty getEventProperty() {
        return eventProperty;
    }

    public void setEventProperty(EventProperty eventProperty) {
        this.eventProperty = eventProperty;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public String getUnhashedPassword() {
        return unhashedPassword;
    }

    public void setUnhashedPassword(String unhashedPassword) {
        this.unhashedPassword = unhashedPassword;
    }
}
