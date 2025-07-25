package gila.notification.application.gateways;

import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.repositories.ChannelSubscriptionRepository;
import gila.notification.infrastructure.orm.ChannelSubscriptionORM;
import gila.notification.infrastructure.orm.id.ChannelSubId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChannelSubscriptionGatewayImplTest {

    @Mock
    private ChannelSubscriptionRepository repository;

    @InjectMocks
    private ChannelSubscriptionGatewayImpl gateway;

    @Test
    void shouldReturnMappedChannelSubscriptions_whenUserHasSubscriptions() {
        final var userId = 42L;
        final var ormList = List.of(createChannelSubscriptionORM(userId, ChannelType.EMAIL));

        when(repository.findAllById_UserId(userId)).thenReturn(ormList);

        final var result = gateway.findAllByUserId(userId);

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnEmptyList_whenUserHasNoSubscriptions() {
        final var userId = 99L;

        when(repository.findAllById_UserId(userId)).thenReturn(List.of());

        final var result = gateway.findAllByUserId(userId);

        assertThat(result).isEmpty();
    }

    private ChannelSubscriptionORM createChannelSubscriptionORM(final Long userId, final ChannelType channel) {
        final var id = new ChannelSubId(userId, channel);
        return new ChannelSubscriptionORM(id);
    }
}