package study.notification.adapters.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.notification.adapters.dto.request.CreateNotificationDTO;
import study.notification.adapters.dto.response.CreatedNotificationDTO;
import study.notification.adapters.dto.response.GetNotificationDTO;
import study.notification.adapters.dto.response.PagedResponse;
import study.notification.application.mappers.NotificationMapper;
import study.notification.domain.entities.Notification;
import study.notification.domain.interfaces.facades.NotifyUsersFacade;
import study.notification.domain.interfaces.usecases.GetAllNotificationsPagedUseCase;
import study.notification.domain.interfaces.usecases.GetFilteredNotificationsPagedUseCase;

@Validated
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    final NotifyUsersFacade notifyUsersFacade;
    final GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase;
    final GetFilteredNotificationsPagedUseCase getFilteredNotificationsPagedUseCase;

    public NotificationController(NotifyUsersFacade notifyUsersFacade, GetAllNotificationsPagedUseCase getAllNotificationsPagedUseCase, GetFilteredNotificationsPagedUseCase getFilteredNotificationsPagedUseCase) {
        this.notifyUsersFacade = notifyUsersFacade;
        this.getAllNotificationsPagedUseCase = getAllNotificationsPagedUseCase;
        this.getFilteredNotificationsPagedUseCase = getFilteredNotificationsPagedUseCase;
    }


    @PostMapping
    public ResponseEntity<CreatedNotificationDTO> sendNotifications(@Valid @RequestBody(required = true) final CreateNotificationDTO dto) {
        notifyUsersFacade.notifyUsers(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedResponse<GetNotificationDTO>> getAllNotifications(@RequestParam(defaultValue = "10") int size,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(required = false) String category) {
        Page<Notification> result;

        if (category != null) {
            result = getFilteredNotificationsPagedUseCase.execute(page, size, category);
        } else {
            result = getAllNotificationsPagedUseCase.execute(page, size);
        }

        final Page<GetNotificationDTO> dtos = NotificationMapper.toPagedDto(result);
        final PagedResponse<GetNotificationDTO> pagedResponse = new PagedResponse<>(dtos.getContent(), dtos.getNumber(), dtos.getSize(), dtos.getTotalElements(), dtos.getTotalPages());

        return ResponseEntity.ok(pagedResponse);
    }

}
