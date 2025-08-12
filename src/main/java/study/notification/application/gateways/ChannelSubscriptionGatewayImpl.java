package study.notification.application.gateways;

import study.notification.application.mappers.ChannelSubscriptionMapper;
import study.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import study.notification.domain.entities.ChannelSubscription;
import study.notification.domain.interfaces.repositories.ChannelSubscriptionRepository;
import study.notification.infrastructure.orm.ChannelSubscriptionORM;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChannelSubscriptionGatewayImpl implements ChannelSubscriptionGateway {

    private final ChannelSubscriptionRepository repository;

    public ChannelSubscriptionGatewayImpl(ChannelSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ChannelSubscription> findAllByUserId(Long userId) {
        return repository.findAllById_UserId(userId)
                .stream()
                .map(ChannelSubscriptionMapper::toDomain)
                .toList();
    }

    @Override
    public Map<Long, List<ChannelSubscription>> findAllByUsersIds(List<Long> usersIds) {
        List<ChannelSubscriptionORM> channelSubscriptionORMS = repository.findAllById_UserIdIn(usersIds);

        return channelSubscriptionORMS.stream()
                .map(ChannelSubscriptionMapper::toDomain)
                .collect(Collectors.groupingBy(ChannelSubscription::getUserId));
    }
}