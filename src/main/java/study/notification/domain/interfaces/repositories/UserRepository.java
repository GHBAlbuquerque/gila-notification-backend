package study.notification.domain.interfaces.repositories;

import study.notification.infrastructure.orm.UserORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserORM, Long> {
}
