package gila.notification.domain.entities;

import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.enums.NotificationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Notification {

    private Long id;
    private Long userId;
    private CategoryType category;
    private ChannelType channel;
    private String message;
    private LocalDateTime timestamp;
    private NotificationStatus status;
    private int retryCount = 0;

    public Notification(CategoryType category, String message) {
        this.category = Objects.requireNonNull(category);
        this.message = Objects.requireNonNull(message);
        this.status = NotificationStatus.PENDING;
    }

    public Notification(Long id, Long userId, CategoryType category, ChannelType channel, String message, LocalDateTime timestamp, NotificationStatus status) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);
        this.category = Objects.requireNonNull(category);
        this.channel = Objects.requireNonNull(channel);
        this.message = Objects.requireNonNull(message);
        this.timestamp = Objects.requireNonNull(timestamp);
        this.status = Objects.requireNonNull(status);
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }

    public void incrementRetry() { this.retryCount++;}

    public boolean canRetry() {
        return retryCount < 3;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public CategoryType getCategory() {
        return category;
    }

    public ChannelType getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public void setChannel(ChannelType channel) {
        this.channel = channel;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}