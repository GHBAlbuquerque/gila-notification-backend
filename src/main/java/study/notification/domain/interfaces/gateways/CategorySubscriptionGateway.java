package study.notification.domain.interfaces.gateways;

import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;

import java.util.List;

public interface CategorySubscriptionGateway {
    List<CategorySubscription> findAllByCategory(CategoryType category);
}
