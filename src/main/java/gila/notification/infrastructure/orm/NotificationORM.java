package gila.notification.infrastructure.orm;

import gila.notification.domain.enums.ChannelType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name= "notification_logs")
public class NotificationORM {

    @Id
    private Long id;
    private String message;

    @Enumerated(EnumType.STRING)
    private ChannelType type;
}
