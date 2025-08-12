package study.notification.infrastructure.config;

import study.notification.application.gateways.CategorySubscriptionGatewayImpl;
import study.notification.application.gateways.ChannelSubscriptionGatewayImpl;
import study.notification.application.gateways.NotificationGatewayImpl;
import study.notification.application.gateways.UserGatewayImpl;
import study.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import study.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import study.notification.domain.interfaces.gateways.UserGateway;
import study.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import study.notification.domain.interfaces.repositories.ChannelSubscriptionRepository;
import study.notification.domain.interfaces.repositories.NotificationRepository;
import study.notification.domain.interfaces.repositories.UserRepository;
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
