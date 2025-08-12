package study.notification.domain.interfaces.gateways;

import study.notification.domain.entities.User;

import java.util.List;
import java.util.Map;

public interface UserGateway {
    User findById(Long id);
    Map<Long, User> findAllById(List<Long> ids);
    boolean existsById(Long id);
}
