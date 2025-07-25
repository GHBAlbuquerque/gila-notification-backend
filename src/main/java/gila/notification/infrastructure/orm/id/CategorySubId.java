package gila.notification.infrastructure.orm.id;

import gila.notification.domain.enums.CategoryType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategorySubId implements Serializable {

    private Long userId;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public CategorySubId() {
    }

    public CategorySubId(Long userId, CategoryType category) {
        this.userId = userId;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategorySubId that)) return false;
        return Objects.equals(userId, that.userId) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, category);
    }
}
