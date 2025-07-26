package gila.notification.application.gateways;

import gila.notification.domain.entities.User;
import gila.notification.domain.interfaces.repositories.UserRepository;
import gila.notification.infrastructure.orm.UserORM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserGatewayImpl gateway;

    @Test
    void shouldReturnUser_WhenFoundById() {
        final var orm = createUserORM();

        when(repository.findById(orm.getId())).thenReturn(Optional.of(orm));

        final var result = gateway.findById(orm.getId());

        assertThat(result.getId()).isEqualTo(orm.getId());
    }

    @Test
    void shouldReturnNull_WhenUserNotFoundById() {
        final var id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        final var result = gateway.findById(id);

        assertThat(result).isNull();
    }

    @Test
    void shouldReturnTrue_WhenUserExistsById() {
        final var id = 5L;

        when(repository.existsById(id)).thenReturn(true);

        final var result = gateway.existsById(id);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalse_WhenUserDoesNotExistById() {
        final var id = 42L;

        when(repository.existsById(id)).thenReturn(false);

        final var result = gateway.existsById(id);

        assertThat(result).isFalse();
    }

    private UserORM createUserORM() {
        return new UserORM(1L, "Alice", "alice@example.com", "123-456");
    }
}