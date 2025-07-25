package gila.notification.domain.interfaces.gateways;


import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationGateway {

    Notification create(Notification notification);

    Page<Notification> findAll(Pageable pageable);
}
