package gila.notification.adapters.controllers;

import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.adapters.dto.response.CreatedNotificationDTO;
import gila.notification.adapters.dto.response.GetNotificationDTO;
import gila.notification.adapters.dto.response.PagedResponse;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import gila.notification.application.mappers.NotificationMapper;
import gila.notification.domain.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public ResponseEntity<PagedResponse<GetNotificationDTO>> getAllNotifications(@RequestParam int size,
                                                                                 @RequestParam int page) {
        final Page<Notification> result = getAllNotificationsPagedUseCase.execute(size, page);
        final Page<GetNotificationDTO> dtos = NotificationMapper.toPagedDto(result);
        final PagedResponse<GetNotificationDTO> pagedResponse = new PagedResponse<>(dtos.getContent(), dtos.getNumber(), dtos.getSize(), dtos.getTotalElements(), dtos.getTotalPages());

        return ResponseEntity.ok(pagedResponse);
    }
}
