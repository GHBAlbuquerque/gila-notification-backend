package study.notification.application.usecases;

import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateNotificationUseCaseImplTest {

    @Mock
    private NotificationGateway gateway;

    @InjectMocks
    private CreateNotificationUseCaseImpl useCase;

    @Test
    void shouldCreateNotificationWithPreFilledFields() {
        final var user = createUser();
        final var inputNotification = createNotification(user.getId());
        final var savedNotification = createNotification(user.getId());

        when(gateway.create(inputNotification)).thenReturn(savedNotification);

        final var result = useCase.execute(user, inputNotification);

        assertThat(result.getUserId()).isEqualTo(user.getId());
    }

    @Test
    void shouldReturnCreatedNotificationFromGateway() {
        final var user = createUser();
        final var inputNotification = createNotification(user.getId());
        final var expected = createNotification(user.getId());

        when(gateway.create(inputNotification)).thenReturn(expected);

        final var result = useCase.execute(user, inputNotification);

        assertThat(result).isEqualTo(expected);
    }

    private User createUser() {
        return new User(1L, "Alice", "alice@example.com", "123-456");
    }

    private Notification createNotification(Long userId) {
        return new Notification(userId, CategoryType.SPORTS, ChannelType.SMS, "Test message");
    }
}