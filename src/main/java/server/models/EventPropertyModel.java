package server.models;

public class EventPropertyModel {
    private int eventId;
    private String description;
    private String name;
    private int eventPropertyId;

    public EventPropertyModel(int eventId, String description, String name) {
        this.eventId = eventId;
        this.description = description;
        this.name = name;
    }

    public EventPropertyModel(int eventId, String description, String name, int eventPropertyId) {
        this.eventId = eventId;
        this.description = description;
        this.name = name;
        this.eventPropertyId = eventPropertyId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventPropertyId() {
        return eventPropertyId;
    }

    public void setEventPropertyId(int eventPropertyId) {
        this.eventPropertyId = eventPropertyId;
    }
}
