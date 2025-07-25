package gila.notification.infrastructure.config;

import gila.notification.domain.interfaces.sender.NotificationSender;
import gila.notification.infrastructure.senders.EmailNotificationSender;
import gila.notification.infrastructure.senders.PushNotificationSender;
import gila.notification.infrastructure.senders.SmsNotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendersBeanDeclaration {

    @Bean
    public NotificationSender pushNotificationSender() {
        return new PushNotificationSender();
    }

    @Bean
    public NotificationSender emailNotificationSender() {
        return new EmailNotificationSender();
    }

    @Bean
    public NotificationSender smsNotificationSender() {
        return new SmsNotificationSender();
    }
}
