package study.notification.application.mappers;

import study.notification.domain.entities.ChannelSubscription;
import study.notification.infrastructure.orm.ChannelSubscriptionORM;
import study.notification.infrastructure.orm.id.ChannelSubId;

public class ChannelSubscriptionMapper {

    public static ChannelSubscription toDomain(final ChannelSubscriptionORM orm) {
        final ChannelSubId id = orm.getId();

        return new ChannelSubscription(id.getUserId(), id.getChannel());
    }
}
