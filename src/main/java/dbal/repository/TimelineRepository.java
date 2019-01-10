package dbal.repository;

import models.Timeline;

public class TimelineRepository extends AbstractRepository<Timeline, Integer> {
        @Override
        public Class<Timeline> getDomainClass() {
            return Timeline.class;
        }
}

