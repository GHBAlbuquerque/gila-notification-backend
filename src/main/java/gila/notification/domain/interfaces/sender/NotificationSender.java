package gila.notification.domain.interfaces.sender;

import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.ChannelType;

public interface NotificationSender {
    ChannelType getType();
    void send(User user, Notification notification);
}
