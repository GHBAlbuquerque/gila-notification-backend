package study.notification.application.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import study.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;

import java.util.List;

public class GetSubscribedUsersUseCaseImpl implements GetSubscribedUsersUseCase {

    private final CategorySubscriptionGateway gateway;

    public GetSubscribedUsersUseCaseImpl(CategorySubscriptionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<CategorySubscription> execute(final CategoryType categoryType) {
        return gateway.findAllByCategory(categoryType);
    }

    @Override
    public Page<CategorySubscription> executePaged(CategoryType categoryType, Pageable pageRequest) {
        return gateway.findAllByCategoryPaged(categoryType, pageRequest);
    }
}
