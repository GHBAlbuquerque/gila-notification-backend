package gila.notification.infrastructure.beans;

import gila.notification.application.gateways.CategorySubscriptionGatewayImpl;
import gila.notification.application.gateways.ChannelSubscriptionGatewayImpl;
import gila.notification.application.gateways.NotificationGatewayImpl;
import gila.notification.application.gateways.UserGatewayImpl;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.gateways.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanDeclaration {

    //TODO
    @Bean
    public UserGateway userGateway() {
        return new UserGatewayImpl();
    }

    @Bean
    public NotificationGateway notificationGateway() {
        return new NotificationGatewayImpl();
    }

    @Bean
    public ChannelSubscriptionGateway channelSubscriptionGateway() {
        return new ChannelSubscriptionGatewayImpl();
    }

    @Bean
    public CategorySubscriptionGateway categorySubscriptionGateway() {
        return new CategorySubscriptionGatewayImpl();
    }
}
