package gila.notification.domain.interfaces.repositories;

import gila.notification.infrastructure.orm.CategorySubscriptionORM;
import gila.notification.infrastructure.orm.id.CategorySubId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySubscriptionRepository extends JpaRepository<CategorySubscriptionORM, CategorySubId> {

    List<CategorySubscriptionORM> findAllById_Category(String category);
}
