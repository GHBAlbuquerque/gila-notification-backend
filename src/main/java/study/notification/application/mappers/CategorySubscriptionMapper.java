package study.notification.application.mappers;

import study.notification.domain.entities.CategorySubscription;
import study.notification.infrastructure.orm.CategorySubscriptionORM;
import study.notification.infrastructure.orm.id.CategorySubId;

public class CategorySubscriptionMapper {

    public static CategorySubscription toDomain(final CategorySubscriptionORM orm) {
        final CategorySubId id = orm.getId();

        return new CategorySubscription(id.getUserId(), id.getCategory());
    }
}