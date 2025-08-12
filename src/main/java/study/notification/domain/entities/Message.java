package study.notification.domain.entities;

import study.notification.domain.enums.CategoryType;

import java.time.LocalDateTime;

public record Message (
        CategoryType category,
        String content,
        LocalDateTime timestamp
){}
