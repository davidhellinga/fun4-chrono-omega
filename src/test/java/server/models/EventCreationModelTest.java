package server.models;

import models.Event;
import models.EventProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import stubs.PersistenceHandlerStub;
import stubs.RepositoryStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventCreationModelTest {

    private PersistenceHandlerStub persistenceHandler = new PersistenceHandlerStub();
    private RepositoryStub repositoryStub = new RepositoryStub();

    @Test
    void fromEventValidDataSuccessTest() {
        EventCreationModel eventModel = new EventCreationModel(persistenceHandler);
        Event event = repositoryStub.getEvent();
        EventProperty eventProperty = event.getProperties().get(0);
//        EventPropertyModel eventPropertyModel=new EventPropertyModel(eventProperty.getId(),eventProperty.getDescription(),eventProperty.getName(), eventProperty.getEvent().getId());

        eventModel.fromEvent(event);

        assertEquals(eventModel.getName(), event.getName());
        assertEquals(eventModel.getDate(), event.getDate());
        assertEquals(eventModel.getEventId(), event.getId());
        assertEquals(eventModel.getTimelineId(), event.getTimeline().getId());

        EventPropertyModel eventPropertyModel = eventModel.getProperties().get(0);

        assertEquals(eventPropertyModel.getName(), eventProperty.getName());
        assertEquals(eventPropertyModel.getDescription(), eventProperty.getDescription());
        assertEquals(eventPropertyModel.getEventPropertyId(), eventProperty.getId());
        assertEquals(eventPropertyModel.getEventId(), eventProperty.getEvent().getId());

    }

    @Test
    @Disabled("Not implemented yet")
    void validateEvent() {

    }

    @Test
    @Disabled("Not implemented yet")
    void toEvent() {
    }
}