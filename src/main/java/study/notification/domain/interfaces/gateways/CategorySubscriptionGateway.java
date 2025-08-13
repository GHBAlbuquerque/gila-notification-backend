package study.notification.domain.interfaces.gateways;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;

import java.util.List;

public interface CategorySubscriptionGateway {
    List<CategorySubscription> findAllByCategory(CategoryType category);
    Page<CategorySubscription> findAllByCategoryPaged(CategoryType category, Pageable pageRequest);
}
