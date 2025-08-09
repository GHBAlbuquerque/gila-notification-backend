package gila.notification.domain.interfaces.gateways;

import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.infrastructure.orm.id.ChannelSubId;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChannelSubscriptionGateway {
    List<ChannelSubscription> findAllByUserId(Long userId);
    Map<Long, List<ChannelSubscription>> findAllByUsersIds(List<Long> usersIds);
}
