package gila.notification.application.usecases;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.gateways.UserGateway;
import gila.notification.domain.interfaces.sender.NotificationSender;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.entities.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CreateNotificationUseCaseImpl implements CreateNotificationUseCase {

    private final Logger logger = LogManager.getLogger(CreateNotificationUseCaseImpl.class);


    @Override
    public Notification execute(Notification notification) {
        return null; //TODO
    }
}
