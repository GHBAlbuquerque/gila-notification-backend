package study.notification.domain.interfaces.usecases;

import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;

import java.util.List;

public interface GetSubscribedUsersUseCase {
    List<CategorySubscription> execute(CategoryType categoryType);
}
