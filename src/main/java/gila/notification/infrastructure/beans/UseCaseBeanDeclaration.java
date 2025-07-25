package gila.notification.infrastructure.beans;

import gila.notification.application.usecases.*;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import gila.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanDeclaration {

    //TODO
    @Bean
    public CreateNotificationUseCase createNotificationUseCase(NotificationGateway gateway) {
        return new CreateNotificationUseCaseImpl(gateway);
    }

    @Bean
    public GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase(NotificationGateway gateway) {
        return new GetAllNotificationsPagedUseCaseImpl(gateway);
    }

    @Bean
    public GetSubscribedUsersUseCase getSubscribedUsersUseCase(CategorySubscriptionGateway gateway) {
        return new GetSubscribedUsersUseCaseImpl(gateway);
    }

    @Bean
    public GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase(ChannelSubscriptionGateway gateway) {
        return new GetUserChannelPreferenceUseCaseImpl(gateway);
    }

    @Bean
    public SendNotificationUseCase sendNotificationUseCase(NotificationGateway gateway) {
        return new SendNotificationUseCaseImpl(gateway);
    }
}
