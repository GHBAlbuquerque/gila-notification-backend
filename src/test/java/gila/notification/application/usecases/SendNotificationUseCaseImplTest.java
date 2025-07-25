package gila.notification.application.usecases;

import gila.notification.application.exceptions.NotificationDeliveryException;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.enums.NotificationStatus;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.sender.NotificationSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendNotificationUseCaseImplTest {

    @Mock
    private NotificationGateway gateway;

    @Mock
    private NotificationSender sender;

    @InjectMocks
    private SendNotificationUseCaseImpl useCase;

    @Test
    void shouldSendNotificationSuccessfully() {
        final var user = createUser();
        final var notification = createNotification(NotificationStatus.PENDING);

        when(sender.getType()).thenReturn(ChannelType.EMAIL);
        when(gateway.updateStatus(notification.getId(), NotificationStatus.SENT)).thenReturn(notification);

        final var useCaseWithSender = new SendNotificationUseCaseImpl(gateway, List.of(sender));

        doAnswer(invocation -> {
            notification.markAsSent();
            return null;
        }).when(sender).send(user, notification);

        useCaseWithSender.execute(user, notification);

        verify(gateway).updateStatus(notification.getId(), NotificationStatus.SENT);
    }

    @Test
    void shouldMarkAsFailedWhenSenderThrows() {
        final var user = createUser();
        final var notification = createNotification(NotificationStatus.PENDING);

        when(sender.getType()).thenReturn(ChannelType.EMAIL);
        doThrow(NotificationDeliveryException.class).when(sender).send(user, notification);

        final var useCaseWithSender = new SendNotificationUseCaseImpl(gateway, List.of(sender));

        useCaseWithSender.execute(user, notification);

        verify(gateway).updateStatus(notification.getId(), NotificationStatus.FAILED);
    }

    @Test
    void shouldThrowWhenNoSenderMatchesChannel() {
        final var user = createUser();
        final var notification = createNotification(NotificationStatus.PENDING);

        final var useCaseWithNoMatchingSender = new SendNotificationUseCaseImpl(gateway, List.of(sender));

        when(sender.getType()).thenReturn(ChannelType.SMS);

        assertThatThrownBy(() -> useCaseWithNoMatchingSender.execute(user, notification))
                .isInstanceOf(NotificationDeliveryException.class);
    }

    private Notification createNotification(NotificationStatus status) {
        final var notification = new Notification(1L, CategoryType.MOVIES, ChannelType.EMAIL, "message");
        notification.setId(42L);
        notification.setStatus(status);
        return notification;
    }

    private User createUser() {
        return new User(1L, "Alice", "alice@example.com", "555-1234");
    }
}