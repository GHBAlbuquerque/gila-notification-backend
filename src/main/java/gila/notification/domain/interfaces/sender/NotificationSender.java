package gila.notification.domain.interfaces.sender;

import gila.notification.domain.entities.Message;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.ChannelType;

public interface NotificationSender {
    ChannelType getType();
    void send(User user, Message message);
}
