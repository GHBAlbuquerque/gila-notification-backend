package gila.notification.application.mappers;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.infrastructure.orm.CategorySubscriptionORM;
import gila.notification.infrastructure.orm.id.CategorySubId;

public class CategorySubscriptionMapper {

    public static CategorySubscription toDomain(final CategorySubscriptionORM orm) {
        final CategorySubId id = orm.getId();

        return new CategorySubscription(id.getUserId(), id.getCategory());
    }

    public static CategorySubscriptionORM toOrm(final CategorySubscription domain) {
        final CategorySubId id = new CategorySubId(domain.getUserId(), domain.getCategory());

        return new CategorySubscriptionORM(id);
    }
}