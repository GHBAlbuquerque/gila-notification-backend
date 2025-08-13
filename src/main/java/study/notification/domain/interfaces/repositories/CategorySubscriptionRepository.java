package study.notification.domain.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.enums.CategoryType;
import study.notification.infrastructure.orm.CategorySubscriptionORM;
import study.notification.infrastructure.orm.id.CategorySubId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySubscriptionRepository extends JpaRepository<CategorySubscriptionORM, CategorySubId> {

    List<CategorySubscriptionORM> findAllById_Category(CategoryType category);

    Page<CategorySubscriptionORM> findById_Category(CategoryType category, Pageable pageable);

}
