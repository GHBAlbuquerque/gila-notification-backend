package study.notification.application.facades;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import study.notification.adapters.dto.request.CreateNotificationDTO;
import study.notification.domain.entities.CategorySubscription;
import study.notification.domain.entities.ChannelSubscription;
import study.notification.domain.entities.Notification;
import study.notification.domain.entities.User;
import study.notification.domain.enums.CategoryType;
import study.notification.domain.enums.ChannelType;
import study.notification.domain.interfaces.gateways.UserGateway;
import study.notification.domain.interfaces.usecases.CreateNotificationUseCase;
import study.notification.domain.interfaces.usecases.GetSubscribedUsersUseCase;
import study.notification.domain.interfaces.usecases.GetUserChannelPreferenceUseCase;
import study.notification.domain.interfaces.usecases.SendNotificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
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
        ReflectionTestUtils.setField(notifyUsersFacade, "PAGE_SIZE", 5000);
        dto = new CreateNotificationDTO(CategoryType.MOVIES, "Hello World");
        subscription = new CategorySubscription(1L, CategoryType.MOVIES);
        channelSubscription = new ChannelSubscription(1L, ChannelType.SMS);
        user = new User(1L, "Test", "test@example.com", "1234567890");
        notification = new Notification(1L, CategoryType.MOVIES, ChannelType.SMS, "Hello World");
    }

    @Test
    void notifyUsers_success() {
        final Page page = new PageImpl(List.of(subscription), PageRequest.of(0, 5000), 1);
        when(getSubscribedUsersUseCase.executePaged(CategoryType.MOVIES,  PageRequest.of(0, 5000))).thenReturn(page);
        when(userGateway.findAllById(List.of(1L))).thenReturn(Map.of(1L, user));
        when(getUserChannelPreferenceUseCase.findAllByMultipleUsersIds(List.of(1L))).thenReturn(Map.of(1L, List.of(channelSubscription)));
        when(createNotificationUseCase.execute(any(User.class), any(Notification.class))).thenReturn(notification);

        notifyUsersFacade.notifyUsers(dto);

        verify(sendNotificationUseCase).execute(user, notification);
    }

    @Test
    void notifyUsers_noSubscriptions() {
        final Page page = new PageImpl(List.of(), PageRequest.of(0, 5000), 1);
        when(getSubscribedUsersUseCase.executePaged(CategoryType.MOVIES,  PageRequest.of(0, 5000))).thenReturn(page);

        notifyUsersFacade.notifyUsers(dto);

        verifyNoInteractions(userGateway);
        verifyNoInteractions(getUserChannelPreferenceUseCase);
        verifyNoInteractions(createNotificationUseCase);
        verifyNoInteractions(sendNotificationUseCase);
    }

    @Test
    void notifyUsers_userNotFound() {
        final Page page = new PageImpl(List.of(subscription), PageRequest.of(0, 5000), 1);
        when(getSubscribedUsersUseCase.executePaged(CategoryType.MOVIES,  PageRequest.of(0, 5000))).thenReturn(page);
        when(userGateway.findAllById(List.of(1L))).thenReturn(Collections.emptyMap());

        notifyUsersFacade.notifyUsers(dto);

        verifyNoInteractions(createNotificationUseCase);
        verifyNoInteractions(sendNotificationUseCase);
    }

    @Test
    void notifyUsers_noChannelPreferences() {
        final Page page = new PageImpl(List.of(subscription), PageRequest.of(0, 5000), 1);
        when(getSubscribedUsersUseCase.executePaged(CategoryType.MOVIES,  PageRequest.of(0, 5000))).thenReturn(page);
        when(userGateway.findAllById(List.of(1L))).thenReturn(Map.of(1L, user));
        when(getUserChannelPreferenceUseCase.findAllByMultipleUsersIds(List.of(1L))).thenReturn(Collections.emptyMap());

        notifyUsersFacade.notifyUsers(dto);

        verifyNoInteractions(createNotificationUseCase);
        verifyNoInteractions(sendNotificationUseCase);
    }

    @Test
    void notifyUsers_userDeletedBeforeSend() {
        final Page page = new PageImpl(List.of(subscription), PageRequest.of(0, 5000), 1);
        when(getSubscribedUsersUseCase.executePaged(CategoryType.MOVIES,  PageRequest.of(0, 5000))).thenReturn(page);
        Map<Long, User> users = new HashMap<Long, User>();
        users.put(1L, null);
        when(userGateway.findAllById(List.of(1L))).thenReturn(users);
        when(getUserChannelPreferenceUseCase.findAllByMultipleUsersIds(List.of(1L))).thenReturn(Map.of(1L, List.of(channelSubscription)));

        notifyUsersFacade.notifyUsers(dto);

        verifyNoInteractions(createNotificationUseCase);
        verifyNoInteractions(sendNotificationUseCase);
    }
}