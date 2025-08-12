package study.notification.domain.interfaces.usecases;

import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;

public interface SendNotificationUseCase {
    void execute(User user, Notification notification);
}
