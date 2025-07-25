package gila.notification.application.mappers;

import gila.notification.adapters.dto.response.GetNotificationDTO;
import gila.notification.domain.entities.Notification;
import gila.notification.infrastructure.orm.NotificationORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

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


    public static Page<GetNotificationDTO> toPagedDto(Page<Notification> notifications) {
        List<GetNotificationDTO> dtoList = notifications
                .stream()
                .map(NotificationMapper::toDto)
                .toList();

        return new PageImpl<>(dtoList, notifications.getPageable(), notifications.getTotalElements());
    }
}