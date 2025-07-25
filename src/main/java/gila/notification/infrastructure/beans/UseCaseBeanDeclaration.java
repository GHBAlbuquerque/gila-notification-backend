package gila.notification.infrastructure.beans;

import gila.notification.application.usecases.*;
import gila.notification.domain.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanDeclaration {

    //TODO
    @Bean
    public CreateNotificationUseCase createNotificationUseCase() {
        return new CreateNotificationUseCaseImpl();
    }

    @Bean
    public GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase() {
        return new GetAllNotificationsPagedUseCaseImpl();
    }

    @Bean
    public GetSubscribedUsersUseCase getSubscribedUsersUseCase() {
        return new GetSubscribedUsersUseCaseImpl();
    }

    @Bean
    public GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase() {
        return new GetUserChannelPreferenceUseCaseImpl();
    }

    @Bean
    public SendNotificationUseCase sendNotificationUseCase() {
        return new SendNotificationUseCaseImpl();
    }
}
