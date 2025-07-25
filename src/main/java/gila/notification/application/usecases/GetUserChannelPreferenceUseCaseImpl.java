package gila.notification.application.usecases;

import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;

import java.util.List;

public class GetUserChannelPreferenceUseCaseImpl implements GetUserChannelPreferenceUseCase {
    //TODO
    @Override
    public List<ChannelSubscription> execute(Long userId) {
        return null;
    }
}
