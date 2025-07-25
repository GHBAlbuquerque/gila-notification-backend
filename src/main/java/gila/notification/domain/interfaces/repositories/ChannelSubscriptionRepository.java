package gila.notification.domain.interfaces.repositories;

import gila.notification.infrastructure.orm.ChannelSubscriptionORM;
import gila.notification.infrastructure.orm.UserORM;
import gila.notification.infrastructure.orm.id.ChannelSubId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelSubscriptionRepository extends JpaRepository<ChannelSubscriptionORM, ChannelSubId> {

    List<ChannelSubscriptionORM> findAllById_Channel(String channel);
}
