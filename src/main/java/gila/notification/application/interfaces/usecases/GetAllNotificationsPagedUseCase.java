package gila.notification.application.interfaces.usecases;

import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetAllNotificationsPagedUseCase {

    List<Notification> execute(Pageable pageable);
}
