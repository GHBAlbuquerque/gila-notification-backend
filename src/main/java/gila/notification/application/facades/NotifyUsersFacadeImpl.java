package gila.notification.application.facades;

import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
import gila.notification.domain.interfaces.gateways.UserGateway;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public class NotifyUsersFacadeImpl implements NotifyUsersFacade {

    private static final Logger logger = LoggerFactory.getLogger(NotifyUsersFacadeImpl.class);

    private final GetSubscribedUsersUseCase getSubscribedUsersUseCase;
    private final GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase;
    private final CreateNotificationUseCase createNotificationUseCase;
    private final SendNotificationUseCase sendNotificationUseCase;
    private final UserGateway userGateway;

    public NotifyUsersFacadeImpl(GetSubscribedUsersUseCase getSubscribedUsersUseCase, GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase, CreateNotificationUseCase createNotificationUseCase, SendNotificationUseCase sendNotificationUseCase, UserGateway userGateway) {
        this.getSubscribedUsersUseCase = getSubscribedUsersUseCase;
        this.getUserChannelPreferenceUseCase = getUserChannelPreferenceUseCase;
        this.createNotificationUseCase = createNotificationUseCase;
        this.sendNotificationUseCase = sendNotificationUseCase;
        this.userGateway = userGateway;
    }

    @Override
    @Async("notificationExecutor")
    public void notifyUsers(final CreateNotificationDTO dto) {
        final CategoryType category = dto.category();
        final String message = dto.message();

        final List<CategorySubscription> subscriptions = getSubscribedUsersUseCase.execute(category);

        if (subscriptions.isEmpty()) {
            logger.info("No subscriptions found for category {}. Skipping.", category);
            return;
        }

        // NOTE: for scalabity (thinking millions of users), consider:
        // - fetching subscriptions in batches (e.g.: paged queries)
        // - parallelizing notification creation and sending (e.g.: with completable future or external queues)
        notifyUsers(subscriptions, message);
    }

    private void notifyUsers(final List<CategorySubscription> subscriptions, final String message) {
        for (CategorySubscription subscription : subscriptions) {
            Long userId = subscription.getUserId();

            if (!userGateway.existsById(userId)) {
                logger.warn("User {} not found. Skipping.", userId);
                return;
            }

            List<ChannelSubscription> channelPreferences = getUserChannelPreferenceUseCase.execute(userId);

            if (channelPreferences.isEmpty()) {
                logger.warn("User {} has no preferred channels configured. Skipping.", userId);
                return;
            }

            sendNotifications(subscription, channelPreferences, message);
        }
    }

    private void sendNotifications(
            final CategorySubscription categorySubscription,
            final List<ChannelSubscription> channelPreferences,
            final String message) {

        for (ChannelSubscription channelSubscription : channelPreferences) {

            final Long userId = categorySubscription.getUserId();
            final ChannelType channel = channelSubscription.getChannel();

            final Notification notification = new Notification(userId, categorySubscription.getCategory(), channel, message);

            final User user = userGateway.findById(userId);

            if (user == null) {
                logger.warn("User {} no longer exists when sending notification. Skipping.", userId);
                return;
            }

            final Notification saved = createNotificationUseCase.execute(user, notification);

            sendNotificationUseCase.execute(user, saved);
        }
    }
}
