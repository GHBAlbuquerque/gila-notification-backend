package study.notification.domain.interfaces.sender;

import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.ChannelType;

public interface NotificationSender {
    ChannelType getType();
    void send(User user, Notification notification);
}
