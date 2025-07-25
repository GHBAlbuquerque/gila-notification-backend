package gila.notification.application.gateways;

import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.entities.CategorySubscription;
import gila.notification.infrastructure.orm.id.CategorySubId;

import java.util.List;
import java.util.Optional;

public class CategorySubscriptionGatewayImpl implements CategorySubscriptionGateway {
    @Override
    public Optional<CategorySubscription> findById(CategorySubId id) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<CategorySubscription> findAllByUserId(Long userId) {
        return null; //TODO
    }
}
