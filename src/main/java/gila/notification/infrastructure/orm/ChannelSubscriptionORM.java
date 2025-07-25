package gila.notification.infrastructure.orm;

import gila.notification.infrastructure.orm.id.ChannelSubId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "channel_subscription")
public class ChannelSubscriptionORM {

    @EmbeddedId
    private ChannelSubId id;

    public ChannelSubId getId() {
        return id;
    }

    public void setId(ChannelSubId id) {
        this.id = id;
    }
}
