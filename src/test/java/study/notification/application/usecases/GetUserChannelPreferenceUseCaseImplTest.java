package study.notification.application.usecases;

import study.notification.domain.entities.ChannelSubscription;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserChannelPreferenceUseCaseImplTest {

    @Mock
    private ChannelSubscriptionGateway gateway;

    @InjectMocks
    private GetUserChannelPreferenceUseCaseImpl useCase;

    @Test
    void shouldReturnChannelPreferencesSuccessfully() {
        final var userId = 1L;
        final var preferences = List.of(createChannelSubscription(userId, ChannelType.EMAIL));

        when(gateway.findAllByUserId(userId)).thenReturn(preferences);

        final var result = useCase.execute(userId);

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnEmptyList_WhenNoPreferencesExist() {
        final var userId = 2L;

        when(gateway.findAllByUserId(userId)).thenReturn(Collections.emptyList());

        final var result = useCase.execute(userId);

        assertThat(result).isEmpty();
    }

    private ChannelSubscription createChannelSubscription(final Long userId, final ChannelType type) {
        return new ChannelSubscription(userId, type);
    }
}