package study.notification.domain.interfaces.facades;

import study.notification.adapters.dto.request.CreateNotificationDTO;

public interface NotifyUsersFacade {
    void notifyUsers(CreateNotificationDTO dto);
}
