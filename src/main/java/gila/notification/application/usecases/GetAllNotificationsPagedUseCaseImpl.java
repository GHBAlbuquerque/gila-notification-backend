package gila.notification.application.usecases;

import gila.notification.domain.interfaces.gateways.NotificationGateway;
import gila.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class GetAllNotificationsPagedUseCaseImpl implements GetAllNotificationsPagedUseCase {

    private final NotificationGateway gateway;
    private static final String TIMESTAMP_FIELD = "timestamp";

    public GetAllNotificationsPagedUseCaseImpl(NotificationGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Page<Notification> execute(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(TIMESTAMP_FIELD).descending());
        return gateway.findAll(pageable);
    }
}
