package gila.notification.domain.interfaces.facades;

import gila.notification.adapters.dto.request.CreateNotificationDTO;

public interface NotifyUsersFacade {
    void notifyUsers(CreateNotificationDTO dto);
}
