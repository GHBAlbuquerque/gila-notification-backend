package gila.notification.application.usecases;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;

import java.util.List;

public class GetSubscribedUsersUseCaseImpl implements GetSubscribedUsersUseCase {

    private final CategorySubscriptionGateway gateway;

    public GetSubscribedUsersUseCaseImpl(CategorySubscriptionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<CategorySubscription> execute(final CategoryType categoryType) {
        return gateway.findAllByCategory(categoryType.name());
    }
}
