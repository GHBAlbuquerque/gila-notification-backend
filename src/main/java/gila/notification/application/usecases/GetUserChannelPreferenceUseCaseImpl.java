package gila.notification.application.usecases;

import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;

import java.util.List;
import java.util.Map;

public class GetUserChannelPreferenceUseCaseImpl implements GetUserChannelPreferenceUseCase {

    private final ChannelSubscriptionGateway gateway;

    public GetUserChannelPreferenceUseCaseImpl(final ChannelSubscriptionGateway channelSubscriptionGateway) {
        this.gateway = channelSubscriptionGateway;
    }

    @Override
    public List<ChannelSubscription> execute(final Long userId) {
        return gateway.findAllByUserId(userId);
    }

    @Override
    public Map<Long, List<ChannelSubscription>> executeInBatches(List<Long> usersIds) {
        return gateway.findAllByUsersIds(usersIds);
    }
}
