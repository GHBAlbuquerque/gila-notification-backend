package study.notification.domain.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.notification.domain.enums.CategoryType;
import study.notification.infrastructure.orm.NotificationORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationORM, Long> {

    Page<NotificationORM> findAllByCategory(Pageable pageable, CategoryType category);
}
