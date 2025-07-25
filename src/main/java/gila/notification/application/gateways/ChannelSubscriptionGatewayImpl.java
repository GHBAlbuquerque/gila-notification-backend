package gila.notification.application.gateways;

import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.infrastructure.orm.id.ChannelSubId;

import java.util.List;
import java.util.Optional;

public class ChannelSubscriptionGatewayImpl implements ChannelSubscriptionGateway {
    @Override
    public Optional<ChannelSubscription> findById(ChannelSubId id) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<ChannelSubscription> findAllByUserId(Long userId) {
        return null; //TODO
    }
}
