package gila.notification.application.usecases;

import gila.notification.application.exceptions.NotificationDeliveryException;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.sender.NotificationSender;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;

import java.util.List;

public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    private final NotificationGateway gateway;
    private final List<NotificationSender> senders;

    public SendNotificationUseCaseImpl(NotificationGateway gateway, List<NotificationSender> senders) {
        this.gateway = gateway;
        this.senders = senders;
    }

    @Override
    public void execute(final User user, final Notification notification) {

        final NotificationSender sender = senders.stream()
                .filter(s -> s.getType().equals(notification.getChannel()))
                .findFirst()
                .orElseThrow(() -> new NotificationDeliveryException(
                        String.format("No sender found for channel %s", notification.getChannel()))
                );

        try {
            sender.send(user, notification);
            notification.markAsSent();
        } catch (NotificationDeliveryException e) {
            // Already logged & retried inside sender, just update the current state
            notification.markAsFailed();
        } finally {
            gateway.updateStatus(notification.getId(), notification.getStatus());
        }
    }
}
