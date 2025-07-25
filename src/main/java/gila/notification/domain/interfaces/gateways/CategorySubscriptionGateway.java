package gila.notification.domain.interfaces.gateways;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.enums.CategoryType;
import gila.notification.infrastructure.orm.id.CategorySubId;

import java.util.List;
import java.util.Optional;

public interface CategorySubscriptionGateway {
    List<CategorySubscription> findAllByCategory(CategoryType category);
}
