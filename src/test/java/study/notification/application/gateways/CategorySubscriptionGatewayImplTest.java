package study.notification.application.gateways;

import study.notification.domain.enums.CategoryType;
import study.notification.domain.interfaces.repositories.CategorySubscriptionRepository;
import study.notification.infrastructure.orm.CategorySubscriptionORM;
import study.notification.infrastructure.orm.id.CategorySubId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorySubscriptionGatewayImplTest {

    @Mock
    private CategorySubscriptionRepository repository;

    @InjectMocks
    private CategorySubscriptionGatewayImpl gateway;

    @Test
    void shouldReturnMappedCategorySubscriptions_whenCategoryExists() {
        final var category = CategoryType.SPORTS;
        final var ormList = List.of(createCategorySubscriptionORM(1L, category));

        when(repository.findAllById_Category(category)).thenReturn(ormList);

        final var result = gateway.findAllByCategory(category);

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnEmptyList_whenNoSubscriptionsFound() {
        final var category = CategoryType.MOVIES;

        when(repository.findAllById_Category(category)).thenReturn(List.of());

        final var result = gateway.findAllByCategory(category);

        assertThat(result).isEmpty();
    }

    private CategorySubscriptionORM createCategorySubscriptionORM(final Long userId, final CategoryType category) {
        final var id = new CategorySubId(userId, category);
        return new CategorySubscriptionORM(id);
    }
}