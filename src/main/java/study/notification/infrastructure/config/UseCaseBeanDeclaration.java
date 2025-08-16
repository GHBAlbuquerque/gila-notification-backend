package study.notification.infrastructure.config;

import study.notification.application.usecases.*;
import study.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import study.notification.domain.interfaces.gateways.ChannelSubscriptionGateway;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import study.notification.domain.interfaces.sender.NotificationSender;
import study.notification.domain.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UseCaseBeanDeclaration {

    @Bean
    public CreateNotificationUseCase createNotificationUseCase(NotificationGateway gateway) {
        return new CreateNotificationUseCaseImpl(gateway);
    }

    @Bean
    public GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase(NotificationGateway gateway) {
        return new GetAllNotificationsPagedUseCaseImpl(gateway);
    }

    @Bean
    public GetFilteredNotificationsPagedUseCase getFilteredNotificationsPagedUseCase(NotificationGateway gateway) {
        return new GetFilteredNotificationsPagedUseCaseImpl(gateway);
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
    public SendNotificationUseCase sendNotificationUseCase(NotificationGateway gateway, List<NotificationSender> senders) {
        return new SendNotificationUseCaseImpl(gateway, senders);
    }


}
