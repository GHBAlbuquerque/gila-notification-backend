package gila.notification.domain.entities;

import gila.notification.domain.enums.CategoryType;

import java.time.LocalDateTime;

public record Message (
        CategoryType category,
        String content,
        LocalDateTime timestamp
){}
