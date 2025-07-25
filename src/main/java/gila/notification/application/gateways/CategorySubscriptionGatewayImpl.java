package gila.notification.application.gateways;

import gila.notification.application.mappers.CategorySubscriptionMapper;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import gila.notification.infrastructure.orm.id.CategorySubId;

import java.util.List;
import java.util.Optional;

public class CategorySubscriptionGatewayImpl implements CategorySubscriptionGateway {

    private final CategorySubscriptionRepository repository;

    public CategorySubscriptionGatewayImpl(CategorySubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CategorySubscription> findById(CategorySubId id) {
        return repository.findById(id)
                .map(CategorySubscriptionMapper::toDomain);
    }

    @Override
    public List<CategorySubscription> findAllByCategory(String category) {
        return repository.findAllById_Category(category)
                .stream()
                .map(CategorySubscriptionMapper::toDomain)
                .toList();
    }
}
