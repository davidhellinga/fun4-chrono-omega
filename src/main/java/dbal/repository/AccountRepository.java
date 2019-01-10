package dbal.repository;

import models.Account;

public class AccountRepository extends AbstractRepository<Account, Integer> {
        @Override
        public Class<Account> getDomainClass() {
            return Account.class;
        }
}

