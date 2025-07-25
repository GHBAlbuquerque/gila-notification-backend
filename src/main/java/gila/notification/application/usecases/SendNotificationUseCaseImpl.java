package gila.notification.application.usecases;

import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;

public class SendNotificationUseCaseImpl implements SendNotificationUseCase {
    //TODO
    private final NotificationGateway gateway;

    public SendNotificationUseCaseImpl(NotificationGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(User user, Notification notification) {

    }
}
