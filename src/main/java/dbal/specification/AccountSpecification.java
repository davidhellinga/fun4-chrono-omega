package dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AccountSpecification {
    private AccountSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByEmail(String email) {
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("email", email);
            }
        };
    }
}
