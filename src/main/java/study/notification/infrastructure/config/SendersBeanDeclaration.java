package study.notification.infrastructure.config;

import study.notification.domain.interfaces.sender.NotificationSender;
import study.notification.infrastructure.senders.EmailNotificationSender;
import study.notification.infrastructure.senders.PushNotificationSender;
import study.notification.infrastructure.senders.SmsNotificationSender;
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
