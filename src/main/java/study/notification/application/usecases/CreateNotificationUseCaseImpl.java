package study.notification.application.usecases;

import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.NotificationStatus;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import study.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
