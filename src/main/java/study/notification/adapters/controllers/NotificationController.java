package study.notification.adapters.controllers;

import study.notification.adapters.dto.request.CreateNotificationDTO;
import study.notification.adapters.dto.response.CreatedNotificationDTO;
import study.notification.adapters.dto.response.GetNotificationDTO;
import study.notification.adapters.dto.response.PagedResponse;
import study.notification.domain.interfaces.facades.NotifyUsersFacade;
import study.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import study.notification.application.mappers.NotificationMapper;
import study.notification.domain.entities.Notification;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    final NotifyUsersFacade notifyUsersFacade;
    final GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase;

    public NotificationController(NotifyUsersFacade notifyUsersFacade, GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase) {
        this.notifyUsersFacade = notifyUsersFacade;
        this.getAllNotificationsPagedUseCase = getAllNotificationsPagedUseCase;
    }


    @PostMapping
    public ResponseEntity<CreatedNotificationDTO> sendNotifications(@Valid @RequestBody(required = true) final CreateNotificationDTO dto) {
        notifyUsersFacade.notifyUsers(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedResponse<GetNotificationDTO>> getAllNotifications(@RequestParam(defaultValue = "10") int size,
                                                                                 @RequestParam(defaultValue = "0") int page) {
        final Page<Notification> result = getAllNotificationsPagedUseCase.execute(page, size);
        final Page<GetNotificationDTO> dtos = NotificationMapper.toPagedDto(result);
        final PagedResponse<GetNotificationDTO> pagedResponse = new PagedResponse<>(dtos.getContent(), dtos.getNumber(), dtos.getSize(), dtos.getTotalElements(), dtos.getTotalPages());

        return ResponseEntity.ok(pagedResponse);
    }
}
