package gila.notification.application.mappers;

import gila.notification.application.dto.request.CreateNotificationDTO;
import gila.notification.application.dto.response.GetNotificationDTO;
import gila.notification.domain.entities.Notification;
import gila.notification.infrastructure.orm.NotificationORM;

public class NotificationMapper {

    public static Notification toDomain(NotificationORM orm) {
        return new Notification(
                orm.getId(),
                orm.getUserId(),
                orm.getCategory(),
                orm.getChannel(),
                orm.getMessage(),
                orm.getTimestamp(),
                orm.getStatus()
        );
    }

    public static NotificationORM toOrm(Notification domain) {
        NotificationORM orm = new NotificationORM();
        orm.setId(domain.getId());
        orm.setUserId(domain.getUserId());
        orm.setCategory(domain.getCategory());
        orm.setChannel(domain.getChannel());
        orm.setMessage(domain.getMessage());
        orm.setTimestamp(domain.getTimestamp());
        orm.setStatus(domain.getStatus());
        return orm;
    }

    public static Notification toDomain(CreateNotificationDTO dto) {
        return new Notification(
                dto.userId(),
                dto.category(),
                dto.message()
        );
    }

    public static GetNotificationDTO toDto(Notification domain) {
        return new GetNotificationDTO(
                domain.getId(),
                domain.getUserId(),
                domain.getCategory(),
                domain.getChannel(),
                domain.getMessage(),
                domain.getTimestamp(),
                domain.getStatus()
        );
    }
}