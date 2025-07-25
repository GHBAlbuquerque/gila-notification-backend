package gila.notification.domain.interfaces.usecases;

import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;

public interface SendNotificationUseCase {
    void execute(User user, Notification notification);
}
