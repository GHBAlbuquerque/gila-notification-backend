package study.notification.adapters.dto.response;

import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.enums.NotificationStatus;

import java.time.LocalDateTime;

public record GetNotificationDTO(
        Long id,
        Long userId,
        CategoryType category,
        ChannelType channel,
        String message,
        LocalDateTime timestamp,
        NotificationStatus status
){}
