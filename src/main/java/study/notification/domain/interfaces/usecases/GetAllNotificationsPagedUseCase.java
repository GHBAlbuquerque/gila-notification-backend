package study.notification.domain.interfaces.usecases;

import study.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;

public interface GetAllNotificationsPagedUseCase {
    Page<Notification> execute(int page, int size);
}
