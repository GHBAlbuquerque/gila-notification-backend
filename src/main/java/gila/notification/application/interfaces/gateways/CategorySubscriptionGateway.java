package gila.notification.application.interfaces.gateways;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.infrastructure.orm.id.CategorySubId;

import java.util.List;
import java.util.Optional;

public interface CategorySubscriptionGateway {
    Optional<CategorySubscription> findById(CategorySubId id);
    List<CategorySubscription> findAllByUserId(Long userId);
}
