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
import java.util.Map;
import java.util.stream.Collectors;

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

        final List<Long> userIds = subscriptions.stream().map(subs -> subs.getUserId()).collect(Collectors.toList());

        //Bulk fetch users and subscriptions to avoid N+1 problem
        final Map<Long, User> users = userGateway.findAllById(userIds);
        final Map<Long, List<ChannelSubscription>> channelPrefs = getUserChannelPreferenceUseCase.findAllByMultipleUsersIds(userIds);

        notifyUsers(subscriptions, message, users, channelPrefs);
    }

    private void notifyUsers(final List<CategorySubscription> subscriptions,
                             final String message,
                             final Map<Long, User> users,
                             final Map<Long, List<ChannelSubscription>> channelPrefs) {
        for (CategorySubscription subscription : subscriptions) {
            final Long userId = subscription.getUserId();

            final User user = users.get(userId);
            if (user == null) {
                logger.warn("User {} not found. Skipping.", userId);
                continue;
            }

            List<ChannelSubscription> userChannels = channelPrefs.get(userId);
            if (userChannels == null || userChannels.isEmpty()) {
                logger.warn("User {} has no preferred channels configured. Skipping.", userId);
                continue;
            }

            sendNotifications(subscription, userChannels, message, user);
        }
    }

    private void sendNotifications(
            final CategorySubscription categorySubscription,
            final List<ChannelSubscription> channelPreferences,
            final String message,
            final User user) {

        for (ChannelSubscription channelSubscription : channelPreferences) {

            final Long userId = categorySubscription.getUserId();
            final ChannelType channel = channelSubscription.getChannel();

            final Notification notification = new Notification(userId, categorySubscription.getCategory(), channel, message);

            if (user == null) {
                logger.warn("User {} no longer exists when sending notification. Skipping.", userId);
                return;
            }

            final Notification saved = createNotificationUseCase.execute(user, notification);

            sendNotificationUseCase.execute(user, saved);
        }
    }
}
