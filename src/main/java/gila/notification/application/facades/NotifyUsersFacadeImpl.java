package gila.notification.application.facades;

import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;

public class NotifyUsersFacadeImpl implements NotifyUsersFacade {

    private final GetSubscribedUsersUseCase getSubscribedUsersUseCase;
    private final GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase;
    private final CreateNotificationUseCase createNotificationUseCase;
    private final SendNotificationUseCase sendNotificationUseCase;

    public NotifyUsersFacadeImpl(GetSubscribedUsersUseCase getSubscribedUsersUseCase, GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase, CreateNotificationUseCase createNotificationUseCase, SendNotificationUseCase sendNotificationUseCase) {
        this.getSubscribedUsersUseCase = getSubscribedUsersUseCase;
        this.getUserChannelPreferenceUseCase = getUserChannelPreferenceUseCase;
        this.createNotificationUseCase = createNotificationUseCase;
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @Override
    public void notifyUsers(CreateNotificationDTO createNotificationDTO) {
        //TODO
    }
}
