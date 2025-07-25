package gila.notification.infrastructure.orm;

import gila.notification.infrastructure.orm.id.CategorySubId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "category_subscriptions")
public class CategorySubscriptionORM {

    @EmbeddedId
    private CategorySubId id;

    public CategorySubscriptionORM() {
    }

    public CategorySubscriptionORM(CategorySubId id) {
        this.id = id;
    }

    public CategorySubId getId() {
        return id;
    }

    public void setId(CategorySubId id) {
        this.id = id;
    }
}
