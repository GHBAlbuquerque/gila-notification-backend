package study.notification.domain.interfaces.usecases;

import org.springframework.data.domain.Page;
import study.notification.domain.entities.Notification;

public interface GetFilteredNotificationsPagedUseCase {
    Page<Notification> execute(int page, int size, String category);
}
