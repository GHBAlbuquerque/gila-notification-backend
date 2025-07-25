package gila.notification.domain.interfaces.usecases;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.enums.CategoryType;

import java.util.List;

public interface GetSubscribedUsersUseCase {
    List<CategorySubscription> execute(CategoryType categoryType);
}
