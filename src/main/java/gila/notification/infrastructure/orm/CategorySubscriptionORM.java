package gila.notification.infrastructure.orm;

import gila.notification.infrastructure.orm.id.CategorySubId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "category_subscription")
public class CategorySubscriptionORM {

    @EmbeddedId
    private CategorySubId id;
}
