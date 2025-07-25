package gila.notification.application.gateways;

import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NotificationGatewayImpl implements NotificationGateway {
    @Override
    public Notification create(Notification notification) {
        return null; //TODO
    }

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return null; //TODO
    }
}
