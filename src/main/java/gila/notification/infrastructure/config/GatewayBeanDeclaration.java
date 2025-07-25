package gila.notification.infrastructure.config;

import gila.notification.application.gateways.CategorySubscriptionGatewayImpl;
import gila.notification.application.gateways.ChannelSubscriptionGatewayImpl;
import gila.notification.application.gateways.NotificationGatewayImpl;
import gila.notification.application.gateways.UserGatewayImpl;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.gateways.UserGateway;
import gila.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import gila.notification.domain.interfaces.repositories.ChannelSubscriptionRepository;
import gila.notification.domain.interfaces.repositories.NotificationRepository;
import gila.notification.domain.interfaces.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanDeclaration {

    @Bean
    public UserGateway userGateway(UserRepository repository) {
        return new UserGatewayImpl(repository);
    }

    @Bean
    public NotificationGateway notificationGateway(NotificationRepository repository) {
        return new NotificationGatewayImpl(repository);
    }

    @Bean
    public ChannelSubscriptionGateway channelSubscriptionGateway(ChannelSubscriptionRepository repository) {
        return new ChannelSubscriptionGatewayImpl(repository);
    }

    @Bean
    public CategorySubscriptionGateway categorySubscriptionGateway(CategorySubscriptionRepository repository) {
        return new CategorySubscriptionGatewayImpl(repository);
    }
}
