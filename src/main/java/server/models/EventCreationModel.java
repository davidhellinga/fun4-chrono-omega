package server.models;

import models.Event;
import models.EventProperty;
import server.handler.PersistenceHandler;

import java.util.ArrayList;
import java.util.List;

public class EventCreationModel {
    private String name;
    private String date;
    private Integer timelineId;
    private Integer eventId;
    private List<EventPropertyModel> properties = new ArrayList<>();
    private transient PersistenceHandler persistenceHandler;

    public EventCreationModel(PersistenceHandler persistenceHandler) {
        this.persistenceHandler = persistenceHandler;
    }

    public EventCreationModel(String name, String date, int timelineId, int eventId) {
        this.name = name;
        this.date = date;
        this.timelineId = timelineId;
        this.eventId = eventId;
    }

    public EventCreationModel(String name, String date, int timelineId) {
        this.name = name;
        this.date = date;
        this.timelineId = timelineId;
    }

    public void setPersistenceHandler(PersistenceHandler persistenceHandler) {
        this.persistenceHandler = persistenceHandler;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(int timelineId) {
        this.timelineId = timelineId;
    }

    public List<EventPropertyModel> getProperties() {
        return properties;
    }

    public void setProperties(List<EventPropertyModel> properties) {
        this.properties = properties;
    }

    public void addProperty(EventPropertyModel eventProperty) {
        this.properties.add(eventProperty);
    }

    public boolean validateEvent() {
        return name != null && date != null && timelineId != null;
    }

    public Event toEvent(Event event) {
        if (event == null) {
            event = new Event();
        }
        event.setName(this.name);
        event.setDate(this.date);
        event.setTimeline(persistenceHandler.getTimelineById(timelineId));
        event.setProperties(new ArrayList<>());
        for (EventPropertyModel eventProperty :
                this.properties) {
            EventProperty property = new EventProperty();
            property.setEvent(event);
            property.setDescription(eventProperty.getDescription());
            property.setName(eventProperty.getName());
            event.addProperty(property);
        }
        return event;
    }

    public void fromEvent(Event event) {
        if (event == null) return;
        name = event.getName();
        date = event.getDate();
        eventId = event.getId();
        for (EventProperty property :
                event.getProperties()) {
            EventPropertyModel propertyModel = new EventPropertyModel(eventId, property.getDescription(), property.getName(), property.getId());
            properties.add(propertyModel);
        }
    }
}
