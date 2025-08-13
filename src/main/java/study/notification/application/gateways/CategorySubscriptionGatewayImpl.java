package study.notification.application.gateways;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import study.notification.application.mappers.CategorySubscriptionMapper;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import study.notification.infrastructure.orm.CategorySubscriptionORM;

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

    @Override
    public Page<CategorySubscription> findAllByCategoryPaged(CategoryType category, Pageable pageRequest) {
        final Page<CategorySubscriptionORM> result = repository.findById_Category(category, pageRequest);
        final List<CategorySubscription> domainResult = result.getContent()
                .stream()
                .map(CategorySubscriptionMapper::toDomain)
                .toList();

        return new PageImpl<>(domainResult, pageRequest, result.getTotalElements());
    }
}
