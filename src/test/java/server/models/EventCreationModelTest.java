package server.models;

import models.Event;
import models.EventProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import stubs.PersistenceHandlerStub;
import stubs.RepositoryStub;

import static org.junit.jupiter.api.Assertions.*;

class EventCreationModelTest {

    private PersistenceHandlerStub persistenceHandler = new PersistenceHandlerStub();
    private RepositoryStub repositoryStub = new RepositoryStub();

    @Test
    void fromEventValidDataSuccessTest() {
        EventCreationModel eventModel = new EventCreationModel(persistenceHandler);
        Event event = repositoryStub.getEvent();
        EventProperty eventProperty = event.getProperties().get(0);

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
    void fromEvenNullDataFailureTest() {
        EventCreationModel eventModel = new EventCreationModel(persistenceHandler);

        eventModel.fromEvent(null);

        assertNull(eventModel.getName());
        assertNull(eventModel.getDate());
        assertNull(eventModel.getEventId());
        assertNull(eventModel.getTimelineId());
        assertEquals(eventModel.getProperties().size(), 0);

    }

    @Test
    void validateEventValidDataSuccessTest() {
        Event event = repositoryStub.getEvent();
        EventCreationModel eventModel = new EventCreationModel(persistenceHandler);
        EventProperty eventProperty = event.getProperties().get(0);
        EventPropertyModel eventPropertyModel = new EventPropertyModel(eventProperty.getId(), eventProperty.getDescription(), eventProperty.getName(), eventProperty.getEvent().getId());
        validateEventPrep(eventModel, eventPropertyModel, event.getId(), event.getDate(), event.getName(), event.getTimeline().getId());

        assertTrue(eventModel.validateEvent());
    }

    @Test
    void validateEventNoDataFailureTest() {
        Event event = repositoryStub.getEvent();
        EventCreationModel eventModel = new EventCreationModel(persistenceHandler);
        EventProperty eventProperty = event.getProperties().get(0);

        assertFalse(eventModel.validateEvent());


    }

    private void validateEventPrep(EventCreationModel eventModel, EventPropertyModel eventPropertyModel, Integer eventId, String eventDate, String eventName, Integer timelineId) {
        eventModel.setEventId(eventId);
        eventModel.setDate(eventDate);
        eventModel.setName(eventName);
        eventModel.setTimelineId(timelineId);
        eventModel.addProperty(eventPropertyModel);
    }

    @Test
    @Disabled("Not implemented yet")
    void toEvent() {
    }
}