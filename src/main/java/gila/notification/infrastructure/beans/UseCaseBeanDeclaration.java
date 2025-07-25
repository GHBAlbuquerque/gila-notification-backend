package gila.notification.infrastructure.beans;

import gila.notification.application.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.application.interfaces.usecases.GetAllNotificationsPagedUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanDeclaration {

    //TODO
    @Bean
    public CreateNotificationUseCase createNotificationUseCase() {
        return null;
    }

    @Bean
    public GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase() {
        return null;
    }
}
