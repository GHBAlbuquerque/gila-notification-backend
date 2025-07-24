package gila.notification.infrastructure.orm.id;

import gila.notification.domain.enums.CategoryType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

@Embeddable
public class CategorySubId implements Serializable {

    private Long userId;

    @Enumerated(EnumType.STRING)
    private CategoryType category;
}
