package gila.notification.application.mappers;

import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.infrastructure.orm.ChannelSubscriptionORM;
import gila.notification.infrastructure.orm.id.ChannelSubId;

public class ChannelSubscriptionMapper {

    public static ChannelSubscription toDomain(final ChannelSubscriptionORM orm) {
        final ChannelSubId id = orm.getId();

        return new ChannelSubscription(id.getUserId(), id.getChannel());
    }

    public static ChannelSubscriptionORM toOrm(final ChannelSubscription domain) {
        final ChannelSubId id = new ChannelSubId(domain.getUserId(), domain.getChannel());

        return new ChannelSubscriptionORM(id);
    }
}
