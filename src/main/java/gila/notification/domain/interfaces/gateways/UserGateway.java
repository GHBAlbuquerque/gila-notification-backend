package gila.notification.domain.interfaces.gateways;

import gila.notification.domain.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserGateway {
    User findById(Long id);
    Map<Long, User> findAllById(List<Long> ids);
    boolean existsById(Long id);
}
