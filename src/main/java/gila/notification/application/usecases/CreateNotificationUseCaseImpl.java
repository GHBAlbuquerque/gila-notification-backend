package gila.notification.application.usecases;

import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.NotificationStatus;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class CreateNotificationUseCaseImpl implements CreateNotificationUseCase {

    private final Logger logger = LogManager.getLogger(CreateNotificationUseCaseImpl.class);
    private final NotificationGateway gateway;

    public CreateNotificationUseCaseImpl(NotificationGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Notification execute(final User user, final Notification notification) {
        if (notification.getUserId() == null) {
            notification.setUserId(user.getId());
        }

        if (notification.getStatus() == null) {
            notification.setStatus(NotificationStatus.PENDING);
        }

        final Notification created = gateway.create(notification);
        logger.info("Created notification {} for user {}", created.getId(), created.getUserId());
        return created;
    }
}
