package gila.notification.infrastructure.beans;

import gila.notification.application.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.application.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.application.interfaces.gateways.NotificationGateway;
import gila.notification.application.interfaces.gateways.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanDeclaration {

    //TODO
    @Bean
    public UserGateway userGateway() {
        return null;
    }

    @Bean
    public NotificationGateway notificationGateway() {
        return null;
    }

    @Bean
    public ChannelSubscriptionGateway channelSubscriptionGateway() {
        return null;
    }

    @Bean
    public CategorySubscriptionGateway categorySubscriptionGateway() {
        return null;
    }
}
