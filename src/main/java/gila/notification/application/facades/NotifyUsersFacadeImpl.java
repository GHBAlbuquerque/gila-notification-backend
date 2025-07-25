package gila.notification.application.facades;

import gila.notification.adapters.controllers.NotificationController;
import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NotifyUsersFacadeImpl implements NotifyUsersFacade {

    private static final Logger logger = LoggerFactory.getLogger(NotifyUsersFacadeImpl.class);

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
    public void notifyUsers(final CreateNotificationDTO dto) {
        final CategoryType category = dto.category();
        final String message = dto.message();

        final List<CategorySubscription> subscriptions = getSubscribedUsersUseCase.execute(category);

        for (CategorySubscription subscription : subscriptions) {
            notifyUser(subscription, message);
        }
    }

    private void notifyUser(final CategorySubscription subscription, String message){
            Long userId = subscription.getUserId();

            List<ChannelSubscription> channelPreferences = getUserChannelPreferenceUseCase.execute(userId);

            if (channelPreferences.isEmpty()) {
                logger.info("User {} has no preferred channels configured. Skipping.", userId);
                return;
            }

            for (ChannelSubscription channelPref : channelPreferences) {
                sendNotification(subscription.getUserId(), subscription, channelPref, message);
            }
    }

    private void sendNotification(final Long userId,
                                                       final CategorySubscription categorySubscription,
                                                       final ChannelSubscription channelSubscription,
                                                       final String message) {

        final ChannelType channel = channelSubscription.getChannel();

        final Notification notification = new Notification(userId, categorySubscription.getCategory(), channel, message);

        final User user = null; //TODO

        final Notification saved = createNotificationUseCase.execute(user, notification);

        sendNotificationUseCase.execute(user, saved);
    }
}
