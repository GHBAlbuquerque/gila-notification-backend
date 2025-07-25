package gila.notification.application.interfaces.repositories;

import gila.notification.infrastructure.orm.ChannelSubscriptionORM;
import gila.notification.infrastructure.orm.UserORM;
import gila.notification.infrastructure.orm.id.ChannelSubId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelSubscriptionRepository extends JpaRepository<ChannelSubscriptionORM, ChannelSubId> {
}
