package gila.notification.application.interfaces.usecases;

import gila.notification.domain.entities.Notification;

public interface CreateNotificationUseCase {
    Notification execute(Notification notification);
}
