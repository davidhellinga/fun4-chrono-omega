package dbal.repository;

import models.Event;

public class EventRepository extends AbstractRepository<Event, Integer> {
        @Override
        public Class<Event> getDomainClass() {
            return Event.class;
        }
}

