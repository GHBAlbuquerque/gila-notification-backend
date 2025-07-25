package gila.notification.adapters.dto.request;


import gila.notification.domain.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateNotificationDTO (
         @NotNull CategoryType category,
         @NotBlank String message
){}
