package gila.notification.domain.interfaces.gateways;


import gila.notification.domain.entities.Notification;
import gila.notification.domain.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationGateway {

    Notification create(Notification notification);

    Notification findById(Long id);

    Notification updateStatus(Long id, NotificationStatus status);

    Page<Notification> findAll(Pageable pageable);
}
