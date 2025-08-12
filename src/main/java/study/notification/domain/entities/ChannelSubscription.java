package study.notification.domain.entities;

import study.notification.domain.enums.ChannelType;

import java.util.Objects;

public class ChannelSubscription {
    private final Long userId;
    private final ChannelType channel;

    public ChannelSubscription(Long userId, ChannelType channel) {
        this.userId = Objects.requireNonNull(userId);
        this.channel = Objects.requireNonNull(channel);
    }

    public Long getUserId() {
        return userId;
    }

    public ChannelType getChannel() {
        return channel;
    }
}