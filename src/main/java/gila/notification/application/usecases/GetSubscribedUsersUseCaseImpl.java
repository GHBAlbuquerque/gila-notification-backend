package gila.notification.application.usecases;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;

import java.util.List;

public class GetSubscribedUsersUseCaseImpl implements GetSubscribedUsersUseCase {
    //TODO

    @Override
    public List<CategorySubscription> execute(CategoryType categoryType) {
        return null;
    }
}
