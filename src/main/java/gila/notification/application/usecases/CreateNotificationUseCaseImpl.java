package gila.notification.application.usecases;

import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateNotificationUseCaseImpl implements CreateNotificationUseCase {

    private final Logger logger = LogManager.getLogger(CreateNotificationUseCaseImpl.class);

    //TODO

    @Override
    public Notification execute(User user, Notification notification) {
        return null; //TODO
    }
}
