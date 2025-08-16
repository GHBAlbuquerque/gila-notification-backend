package study.notification.domain.interfaces.gateways;


import study.notification.domain.entities.Notification;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationGateway {

    Notification create(Notification notification);

    Notification findById(Long id);

    Notification updateStatus(Long id, NotificationStatus status);

    Page<Notification> findAll(Pageable pageable);

    Page<Notification> findAllByCategory(Pageable pageable, String category);
}
