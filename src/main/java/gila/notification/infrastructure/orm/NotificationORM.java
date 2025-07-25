package gila.notification.infrastructure.orm;

import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.enums.NotificationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name= "notification_logs")
public class NotificationORM {

    @Id
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChannelType channel;

    @NotBlank
    private String message;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    public NotificationORM() {
    }

    public NotificationORM(Long id, Long userId, CategoryType category, ChannelType channel, String message, LocalDateTime timestamp, NotificationStatus status) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.channel = channel;
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public ChannelType getChannel() {
        return channel;
    }

    public void setChannel(ChannelType channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
