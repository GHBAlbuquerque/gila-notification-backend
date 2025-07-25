package gila.notification.application.exceptions;

public class NotificationDeliveryException extends RuntimeException{
    public NotificationDeliveryException(String message) {
        super(message);
    }
}
