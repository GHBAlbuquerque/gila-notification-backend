package gila.notification.domain.entities;

import gila.notification.domain.enums.CategoryType;

import java.time.Instant;
import java.util.Objects;

public class Notification {
    private final Long id;
    private final CategoryType category;
    private final String message;
    private final Instant createdAt;

    public Notification(Long id, CategoryType category, String message, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.category = Objects.requireNonNull(category);
        this.message = Objects.requireNonNull(message);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public Long getId() {
        return id;
    }

    public CategoryType getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}