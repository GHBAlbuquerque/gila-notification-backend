package gila.notification.application.usecases;

import gila.notification.domain.entities.CategorySubscription;
import gila.notification.domain.enums.CategoryType;
import gila.notification.domain.interfaces.gateways.CategorySubscriptionGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSubscribedUsersUseCaseImplTest {

    @Mock
    private CategorySubscriptionGateway gateway;

    @InjectMocks
    private GetSubscribedUsersUseCaseImpl useCase;

    @Test
    void shouldReturnSubscriptionsSuccessfully() {
        final var category = CategoryType.SPORTS;
        final var subscriptions = List.of(createSubscription(1L, category));

        when(gateway.findAllByCategory(category)).thenReturn(subscriptions);

        final var result = useCase.execute(category);

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnEmptyListWhenNoSubscriptions() {
        final var category = CategoryType.MOVIES;

        when(gateway.findAllByCategory(category)).thenReturn(Collections.emptyList());

        final var result = useCase.execute(category);

        assertThat(result).isEmpty();
    }

    private CategorySubscription createSubscription(final Long userId, final CategoryType category) {
        return new CategorySubscription(userId, category);
    }
}