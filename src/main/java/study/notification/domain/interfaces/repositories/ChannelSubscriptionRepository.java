package study.notification.domain.interfaces.repositories;

import study.notification.infrastructure.orm.ChannelSubscriptionORM;
import study.notification.infrastructure.orm.id.ChannelSubId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelSubscriptionRepository extends JpaRepository<ChannelSubscriptionORM, ChannelSubId> {

    List<ChannelSubscriptionORM> findAllById_UserId(Long userId);
    List<ChannelSubscriptionORM> findAllById_UserIdIn(List<Long> usersIds);

}
