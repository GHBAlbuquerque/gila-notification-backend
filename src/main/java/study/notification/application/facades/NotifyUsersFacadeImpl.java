package study.notification.application.facades;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import study.notification.adapters.dto.request.CreateNotificationDTO;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.entities.ChannelSubscription;
import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.interfaces.facades.NotifyUsersFacade;
import study.notification.domain.interfaces.gateways.UserGateway;
import study.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import study.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import study.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import study.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

public class NotifyUsersFacadeImpl implements NotifyUsersFacade {

    @Value("${database.max.page.size}")
    private Integer PAGE_SIZE;

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

        int currentPage = 0;
        Page<CategorySubscription> subscriptionPage;

        do {
            // get paginated subscriptions for batch processing
            subscriptionPage = getSubscribedUsersUseCase.executePaged(category, PageRequest.of(currentPage, PAGE_SIZE));

            if(subscriptionPage.getTotalElements() == 0) {
                logger.info("No subscriptions found for category {}. Skipping.", category);
                break;
            }

            List<Long> usersIds = subscriptionPage.getContent().stream().map(CategorySubscription::getUserId).toList();

            // Bulk fetch all users and their channel subscriptions in this lot
            Map<Long, User> usersMap = userGateway.findAllById(usersIds);
            Map<Long, List<ChannelSubscription>> channelSubscriptionMap = getUserChannelPreferenceUseCase.findAllByMultipleUsersIds(usersIds);

            // This could be submitted to anoher thread, using a newly created executor
            // Example: executor.submit(() -> batchNotifyUsers(subscriptionPage.getContent(), message, usersMap, channelSubscriptionMap));
            batchNotifyUsers(subscriptionPage.getContent(),message, usersMap, channelSubscriptionMap);

            // Do for the next page
            currentPage++;

        } while(subscriptionPage.hasNext());
    }

    private void batchNotifyUsers(final List<CategorySubscription> subscriptions,
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
