package gila.notification.application.dto.response;

import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.enums.NotificationStatus;

import java.time.LocalDateTime;

public record GetNotificationDTO(
        Long userId,
        CategoryType category,
        ChannelType channel,
        String message,
        LocalDateTime timestamp,
        NotificationStatus status
){}
