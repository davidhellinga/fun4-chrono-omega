package dbal.repository;

import models.Dateformat;

public class DateformatRepository extends AbstractRepository<Dateformat, Integer> {
        @Override
        public Class<Dateformat> getDomainClass() {
            return Dateformat.class;
        }
}

