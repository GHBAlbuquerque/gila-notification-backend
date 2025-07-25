package gila.notification.domain.interfaces.usecases;

import gila.notification.domain.entities.ChannelSubscription;

import java.util.List;

public interface GetUserChannelPreferenceUseCase {
    List<ChannelSubscription> execute(Long userId);
}
