package gila.notification.application.facades;

import gila.notification.adapters.dto.request.CreateNotificationDTO;
import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.entities.ChannelSubscription;
import gila.notification.domain.entities.Notification;
import gila.notification.domain.entities.User;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.enums.ChannelType;
import gila.notification.domain.interfaces.gateways.UserGateway;
import gila.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import gila.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import gila.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import gila.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifyUsersFacadeImplTest {

    @Mock
    private GetSubscribedUsersUseCase getSubscribedUsersUseCase;

    @Mock
    private GetUserChannelPreferenceUseCase getUserChannelPreferenceUseCase;

    @Mock
    private CreateNotificationUseCase createNotificationUseCase;

    @Mock
    private SendNotificationUseCase sendNotificationUseCase;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private NotifyUsersFacadeImpl notifyUsersFacade;

    private CreateNotificationDTO dto;
    private CategorySubscription subscription;
    private ChannelSubscription channelSubscription;
    private User user;
    private Notification notification;

    @BeforeEach
    void setUp() {
        dto = new CreateNotificationDTO(CategoryType.MOVIES, "Hello World");
        subscription = new CategorySubscription(1L, CategoryType.MOVIES);
        channelSubscription = new ChannelSubscription(1L, ChannelType.SMS);
        user = new User(1L, "Test", "test@example.com", "1234567890");
        notification = new Notification(1L, CategoryType.MOVIES, ChannelType.SMS, "Hello World");
    }

    @Test
    void notifyUsers_success() {
        when(getSubscribedUsersUseCase.execute(CategoryType.MOVIES)).thenReturn(List.of(subscription));
        when(userGateway.existsById(1L)).thenReturn(true);
        when(getUserChannelPreferenceUseCase.execute(1L)).thenReturn(List.of(channelSubscription));
        when(userGateway.findById(1L)).thenReturn(user);
        when(createNotificationUseCase.execute(any(User.class), any(Notification.class))).thenReturn(notification);

        notifyUsersFacade.notifyUsers(dto);

        verify(sendNotificationUseCase).execute(user, notification);
    }

    @Test
    void notifyUsers_noSubscriptions() {
        when(getSubscribedUsersUseCase.execute(CategoryType.MOVIES)).thenReturn(Collections.emptyList());

        notifyUsersFacade.notifyUsers(dto);

        verifyNoInteractions(userGateway);
    }

    @Test
    void notifyUsers_userNotFound() {
        when(getSubscribedUsersUseCase.execute(CategoryType.MOVIES)).thenReturn(List.of(subscription));
        when(userGateway.existsById(1L)).thenReturn(false);

        notifyUsersFacade.notifyUsers(dto);

        verify(userGateway, never()).findById(1L);
    }

    @Test
    void notifyUsers_noChannelPreferences() {
        when(getSubscribedUsersUseCase.execute(CategoryType.MOVIES)).thenReturn(List.of(subscription));
        when(userGateway.existsById(1L)).thenReturn(true);
        when(getUserChannelPreferenceUseCase.execute(1L)).thenReturn(Collections.emptyList());

        notifyUsersFacade.notifyUsers(dto);

        verify(createNotificationUseCase, never()).execute(any(), any());
    }

    @Test
    void notifyUsers_userDeletedBeforeSend() {
        when(getSubscribedUsersUseCase.execute(CategoryType.MOVIES)).thenReturn(List.of(subscription));
        when(userGateway.existsById(1L)).thenReturn(true);
        when(getUserChannelPreferenceUseCase.execute(1L)).thenReturn(List.of(channelSubscription));
        when(userGateway.findById(1L)).thenReturn(null);

        notifyUsersFacade.notifyUsers(dto);

        verify(createNotificationUseCase, never()).execute(any(), any());
    }
}