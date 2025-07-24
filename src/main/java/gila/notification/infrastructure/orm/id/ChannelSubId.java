package gila.notification.infrastructure.orm.id;

import gila.notification.domain.enums.ChannelType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

@Embeddable
public class ChannelSubId implements Serializable {

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ChannelType channel;
}
