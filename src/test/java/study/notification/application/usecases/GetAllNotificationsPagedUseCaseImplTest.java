package study.notification.application.usecases;

import study.notification.domain.entities.Notification;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllNotificationsPagedUseCaseImplTest {

    @Mock
    private NotificationGateway gateway;

    @InjectMocks
    private GetAllNotificationsPagedUseCaseImpl useCase;

    @Test
    void shouldReturnPagedNotificationsSuccessfully() {
        final var page = 0;
        final var size = 2;
        final var notifications = List.of(createNotification(1L), createNotification(2L));
        final var pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        final var pageResult = new PageImpl<>(notifications, pageable, notifications.size());

        when(gateway.findAll(pageable)).thenReturn(pageResult);

        final var result = useCase.execute(page, size);

        assertThat(result.getTotalElements()).isEqualTo(notifications.size());
    }

    @Test
    void shouldReturnEmptyPage_WhenNoNotifications() {
        final var page = 1;
        final var size = 5;
        final var pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        final var pageResult = new PageImpl<Notification>(List.of(), pageable, 0);

        when(gateway.findAll(pageable)).thenReturn(pageResult);

        final var result = useCase.execute(page, size);

        assertThat(result.getTotalElements()).isZero();
    }

    private Notification createNotification(Long userId) {
        return new Notification(userId, CategoryType.SPORTS, ChannelType.SMS, "Test message");
    }
}