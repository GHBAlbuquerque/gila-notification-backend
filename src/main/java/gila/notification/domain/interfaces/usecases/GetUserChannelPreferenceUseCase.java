package gila.notification.domain.interfaces.usecases;

import gila.notification.domain.entities.ChannelSubscription;

import java.util.List;
import java.util.Map;

public interface GetUserChannelPreferenceUseCase {
    List<ChannelSubscription> execute(Long userId);
    Map<Long, List<ChannelSubscription>> findAllByMultipleUsersIds(List<Long> usersIds);
}
