package gila.notification.domain.interfaces.gateways;

import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.infrastructure.orm.id.ChannelSubId;

import java.util.List;
import java.util.Optional;

public interface ChannelSubscriptionGateway {
    Optional<ChannelSubscription> findById(ChannelSubId id);
    List<ChannelSubscription> findAllByUserId(Long userId);
}
