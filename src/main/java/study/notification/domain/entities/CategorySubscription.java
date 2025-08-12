package study.notification.domain.entities;

import study.notification.domain.enums.CategoryType;

import java.util.Objects;

public class CategorySubscription {
    private final Long userId;
    private final CategoryType category;

    public CategorySubscription(Long userId, CategoryType category) {
        this.userId = Objects.requireNonNull(userId);
        this.category = Objects.requireNonNull(category);
    }

    public Long getUserId() {
        return userId;
    }

    public CategoryType getCategory() {
        return category;
    }
}