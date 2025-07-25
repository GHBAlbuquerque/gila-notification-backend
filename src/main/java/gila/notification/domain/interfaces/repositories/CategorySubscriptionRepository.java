package gila.notification.domain.interfaces.repositories;

import gila.notification.infrastructure.orm.CategorySubscriptionORM;
import gila.notification.infrastructure.orm.UserORM;
import gila.notification.infrastructure.orm.id.CategorySubId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorySubscriptionRepository extends JpaRepository<CategorySubscriptionORM, CategorySubId> {
}
