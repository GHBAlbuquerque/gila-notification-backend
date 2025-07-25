package gila.notification.application.interfaces.repositories;

import gila.notification.infrastructure.orm.NotificationORM;
import gila.notification.infrastructure.orm.UserORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationORM, Long> {
}
