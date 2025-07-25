package gila.notification.application.interfaces.usecases;

import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;

public interface GetAllNotificationsPagedUseCase {

    Page<Notification> execute(int page, int size);
}
