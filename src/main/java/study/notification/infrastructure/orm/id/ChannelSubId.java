package study.notification.infrastructure.orm.id;

import study.notification.domain.enums.ChannelType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChannelSubId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "channel")
    @Enumerated(EnumType.STRING)
    private ChannelType channel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ChannelType getChannel() {
        return channel;
    }

    public void setChannel(ChannelType channel) {
        this.channel = channel;
    }

    public ChannelSubId() {
    }

    public ChannelSubId(Long userId, ChannelType channel) {
        this.userId = userId;
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelSubId that)) return false;
        return Objects.equals(userId, that.userId) && channel == that.channel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, channel);
    }
}
