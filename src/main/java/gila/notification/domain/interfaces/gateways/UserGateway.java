package gila.notification.domain.interfaces.gateways;

import gila.notification.domain.entities.User;

import java.util.Optional;

public interface UserGateway {
    User findById(Long id);
    boolean existsById(Long id);
}
