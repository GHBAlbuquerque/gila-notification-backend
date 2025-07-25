package gila.notification.adapters.controllers;

import gila.notification.application.dto.request.CreateNotificationDTO;
import gila.notification.application.dto.response.CreatedNotificationDTO;
import gila.notification.application.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.application.interfaces.usecases.GetAllNotificationsPagedUseCase;
import gila.notification.application.mappers.NotificationMapper;
import gila.notification.domain.entities.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    final CreateNotificationUseCase createNotificationUseCase;
    final GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase;

    public NotificationController(CreateNotificationUseCase createNotificationUseCase, GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase) {
        this.createNotificationUseCase = createNotificationUseCase;
        this.getAllNotificationsPagedUseCase = getAllNotificationsPagedUseCase;
    }

    @PostMapping
    public ResponseEntity<CreatedNotificationDTO> createNotification(@RequestBody final CreateNotificationDTO dto) {
        final Notification notification = NotificationMapper.toDomain(dto);
        final Notification created = createNotificationUseCase.execute(notification);

        return ResponseEntity.ok(NotificationMapper.toCreatedDto(created));
    }
}
