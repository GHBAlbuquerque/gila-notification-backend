package study.notification.domain.interfaces.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;

import java.util.List;

public interface GetSubscribedUsersUseCase {
    List<CategorySubscription> execute(CategoryType categoryType);
    Page<CategorySubscription> executePaged(CategoryType categoryType, Pageable pageRequest);
}
