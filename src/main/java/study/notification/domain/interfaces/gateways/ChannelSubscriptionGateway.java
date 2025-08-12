package study.notification.domain.interfaces.gateways;

import study.notification.domain.entities.ChannelSubscription;

import java.util.List;
import java.util.Map;

public interface ChannelSubscriptionGateway {
    List<ChannelSubscription> findAllByUserId(Long userId);
    Map<Long, List<ChannelSubscription>> findAllByUsersIds(List<Long> usersIds);
}
