package gila.notification.infrastructure.senders;

import gila.notification.domain.entities.Message;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.sender.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushNotificationSender implements NotificationSender {

    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationSender.class);

    @Override
    public ChannelType getType() {
        return ChannelType.PUSH;
    }

    @Override
    public void send(User user, Message message) {
        try {
            logger.info("Mock Push sent to {}: {}", user.getPhoneNumber(), message.content());
        } catch (Exception e) {
            logger.info("Failed to send SMS to {}: {}", user.getPhoneNumber(), message.content());
            // implement a retry mechanism like sending to a queue for retry
        }
    }
}
