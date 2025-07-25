package gila.notification.application.usecases;

import gila.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;

public class GetAllNotificationsPagedUseCaseImpl implements GetAllNotificationsPagedUseCase {

    @Override
    public Page<Notification> execute(int page, int size) {
        return null; //TODO
    }
}
