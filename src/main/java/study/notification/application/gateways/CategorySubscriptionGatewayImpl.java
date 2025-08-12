package study.notification.application.gateways;

import study.notification.application.mappers.CategorySubscriptionMapper;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.interfaces.repositories.CategorySubscriptionRepository;

import java.util.List;

public class CategorySubscriptionGatewayImpl implements CategorySubscriptionGateway {

    private final CategorySubscriptionRepository repository;

    public CategorySubscriptionGatewayImpl(CategorySubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategorySubscription> findAllByCategory(CategoryType category) {
        return repository.findAllById_Category(category)
                .stream()
                .map(CategorySubscriptionMapper::toDomain)
                .toList();
    }
}
