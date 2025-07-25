package gila.notification.application.gateways;

import gila.notification.application.mappers.ChannelSubscriptionMapper;
import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import gila.notification.domain.interfaces.repositories.ChannelSubscriptionRepository;
import gila.notification.infrastructure.orm.id.ChannelSubId;

import java.util.List;
import java.util.Optional;

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
}