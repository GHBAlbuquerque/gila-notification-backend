package gila.notification.adapters.controllers;

import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.adapters.dto.response.CreatedNotificationDTO;
import gila.notification.adapters.dto.response.GetNotificationDTO;
import gila.notification.adapters.dto.response.PagedResponse;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
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

    final NotifyUsersFacade notifyUsersFacade;
    final GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase;

    public NotificationController(NotifyUsersFacade notifyUsersFacade, GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase) {
        this.notifyUsersFacade = notifyUsersFacade;
        this.getAllNotificationsPagedUseCase = getAllNotificationsPagedUseCase;
    }


    @PostMapping
    public ResponseEntity<CreatedNotificationDTO> sendNotification(@RequestBody final CreateNotificationDTO dto) {
        notifyUsersFacade.notifyUsers(dto);
        return ResponseEntity.noContent().build();
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
