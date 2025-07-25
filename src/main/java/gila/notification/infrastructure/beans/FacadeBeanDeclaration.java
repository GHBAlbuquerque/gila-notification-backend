package gila.notification.infrastructure.beans;

import gila.notification.application.facades.NotifyUsersFacadeImpl;
import gila.notification.application.usecases.SendNotificationUseCaseImpl;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeBeanDeclaration {

    @Bean
    public NotifyUsersFacade notifyUsersFacade(GetSubscribedUsersUseCase getSubscribedUsersUseCase,
                                               GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase,
                                               CreateNotificationUseCase createNotificationUseCase,
                                               SendNotificationUseCase sendNotificationUseCase) {
        return new NotifyUsersFacadeImpl(getSubscribedUsersUseCase, getUserChannelPreferenceUseCase, createNotificationUseCase, sendNotificationUseCase);
    }
}
