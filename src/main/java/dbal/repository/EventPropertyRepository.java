package dbal.repository;

import models.EventProperty;

public class EventPropertyRepository extends AbstractRepository<EventProperty, Integer> {
        @Override
        public Class<EventProperty> getDomainClass() {
            return EventProperty.class;
        }
}

