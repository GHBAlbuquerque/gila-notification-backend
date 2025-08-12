package study.notification.infrastructure.config;

import study.notification.application.facades.NotifyUsersFacadeImpl;
import study.notification.domain.interfaces.facades.NotifyUsersFacade;
import study.notification.domain.interfaces.gateways.UserGateway;
import study.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import study.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import study.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import study.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeBeanDeclaration {

    @Bean
    public NotifyUsersFacade notifyUsersFacade(GetSubscribedUsersUseCase getSubscribedUsersUseCase,
                                               GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase,
                                               CreateNotificationUseCase createNotificationUseCase,
                                               SendNotificationUseCase sendNotificationUseCase,
                                               UserGateway userGateway) {
        return new NotifyUsersFacadeImpl(getSubscribedUsersUseCase, getUserChannelPreferenceUseCase, createNotificationUseCase, sendNotificationUseCase, userGateway);
    }
}
