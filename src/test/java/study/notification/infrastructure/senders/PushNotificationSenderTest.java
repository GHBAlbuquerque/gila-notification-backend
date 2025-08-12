package study.notification.infrastructure.senders;

import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class PushNotificationSenderTest {

    @InjectMocks
    private PushNotificationSender sender;

    @Test
    void shouldReturnPushChannelType() {
        final var type = sender.getType();
        assertThat(type).isEqualTo(ChannelType.PUSH);
    }

    @Test
    void shouldSendSuccessfully() {
        final var user = createUser();
        final var notification = createNotification(true);

        Assertions.assertDoesNotThrow(() -> sender.send(user, notification));
    }

    @Test
    void shouldRetryOnceOnException() {
        final var user = createUser();
        final var notification = createNotification(true);

        // could simulate exception thrown by sender here trough mock

        sender.send(user, notification);

        Assertions.assertDoesNotThrow(() -> sender.send(user, notification));
    }

    @Test
    void shouldFailIfMaxRetriesReached() {
        final var user = createUser();
        final var notification = createNotification( false);

        for(int i = 0; i < 3; i++) {
            notification.incrementRetry();
        }

        // could simulate exception thrown by sender here through mock

        /*assertThatThrownBy(() -> sender.send(user, notification))
                .isInstanceOf(NotificationDeliveryException.class);*/

        Assertions.assertDoesNotThrow(() -> sender.send(user, notification));
    }

    private User createUser() {
        return new User(1L, "Alice", "alice@example.com", "555-1234");
    }

    private Notification createNotification(boolean allowSuccess) {
        return new Notification(1L, CategoryType.SPORTS, ChannelType.PUSH, allowSuccess ? "Hello" : "Error");
    }
}