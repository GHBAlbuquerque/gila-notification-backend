package gila.notification.application.gateways;

import gila.notification.application.exceptions.NotificationNotFoundException;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.enums.NotificationStatus;
import gila.notification.domain.interfaces.repositories.NotificationRepository;
import gila.notification.infrastructure.orm.NotificationORM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationGatewayImplTest {

    @Mock
    private NotificationRepository repository;

    @InjectMocks
    private NotificationGatewayImpl gateway;

    @Test
    void shouldCreateNotificationSuccessfully() {
        final var notification = createDomainNotification();
        final var orm = createORMNotification();

        when(repository.save(orm)).thenReturn(orm);

        final var result = gateway.create(eq(notification));

        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnNotificationById() {
        final var id = 10L;
        final var orm = createORMNotification();

        when(repository.findById(id)).thenReturn(Optional.of(orm));

        final var result = gateway.findById(id);

        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void shouldThrowWhenNotificationNotFoundById() {
        final var id = 404L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gateway.findById(id))
                .isInstanceOf(NotificationNotFoundException.class);
    }

    @Test
    void shouldUpdateNotificationStatus() {
        final var id = 1L;
        final var existing = createORMNotification();
        final var updated = createORMNotification();
        updated.setStatus(NotificationStatus.FAILED);

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(updated)).thenReturn(updated);

        final var result = gateway.updateStatus(id, NotificationStatus.FAILED);

        assertThat(result.getStatus()).isEqualTo(NotificationStatus.FAILED);
    }

    @Test
    void shouldReturnPagedNotifications() {
        final var pageable = PageRequest.of(0, 2);
        final var page = new PageImpl<>(List.of(createORMNotification()));

        when(repository.findAll(pageable)).thenReturn(page);

        final var result = gateway.findAll(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    private Notification createDomainNotification() {
        return new Notification(1L, 1L, CategoryType.SPORTS, ChannelType.SMS, "Test message", LocalDateTime.now(),  NotificationStatus.SENT);
    }

    private NotificationORM createORMNotification() {
        return new NotificationORM(1L, 1L, CategoryType.SPORTS, ChannelType.SMS, "Test message", LocalDateTime.now(),  NotificationStatus.SENT);
    }
}