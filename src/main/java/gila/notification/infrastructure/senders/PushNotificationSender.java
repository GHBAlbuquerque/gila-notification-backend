package gila.notification.infrastructure.senders;

import gila.notification.application.exceptions.NotificationDeliveryException;
import gila.notification.domain.entities.Message;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.sender.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static gila.notification.application.exceptions.ErrorMessages.NOTIFICATION_DELIVERY_FAILED;

public class PushNotificationSender implements NotificationSender {

    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationSender.class);

    @Override
    public ChannelType getType() {
        return ChannelType.PUSH;
    }

    @Override
    public void send(User user, Notification notification) {
        try {
            final Message message = new Message(notification.getCategory(), notification.getMessage(), notification.getTimestamp());
            //implement sending logic here

            logger.info("Mock {} sent to {}: {}", getType(), user.getPhoneNumber(), message.content());

        } catch (Exception e) {
            logger.info("Failed to send {} to {}: {}", getType(), user.getPhoneNumber(), notification.getId());
            enqueueForRetry(user, notification);
        }
    }

    private void enqueueForRetry(User user, Notification notification) {
        if(!notification.canRetry()) {
            throw new NotificationDeliveryException(String.format(NOTIFICATION_DELIVERY_FAILED, notification.getId(), getType(), user.getId()));
        }
        notification.incrementRetry();
        // Placeholder for retry mechanism (e.g., queue, in-memory, or DB) with the notification object
        logger.info("Message enqueued for retry for user {}", user.getId());
    }
}
