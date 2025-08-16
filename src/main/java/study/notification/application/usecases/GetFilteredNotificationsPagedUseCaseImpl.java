package study.notification.application.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import study.notification.domain.entities.Notification;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import study.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import study.notification.domain.interfaces.usecases.GetFilteredNotificationsPagedUseCase;

public class GetFilteredNotificationsPagedUseCaseImpl implements GetFilteredNotificationsPagedUseCase {

    private final NotificationGateway gateway;
    private static final String TIMESTAMP_FIELD = "timestamp";

    public GetFilteredNotificationsPagedUseCaseImpl(NotificationGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Page<Notification> execute(int page, int size, String category) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(TIMESTAMP_FIELD).descending());
        return gateway.findAllByCategory(pageable, category);
    }
}
