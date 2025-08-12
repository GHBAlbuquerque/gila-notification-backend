package study.notification.domain.interfaces.repositories;

import study.notification.infrastructure.orm.NotificationORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationORM, Long> {
}
