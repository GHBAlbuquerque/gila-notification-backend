package gila.notification.application.gateways;

import gila.notification.application.interfaces.gateways.UserGateway;
import gila.notification.domain.entities.User;

import java.util.Optional;

public class UserGatewayImpl implements UserGateway {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty(); //TODO
    }

    @Override
    public boolean existsById(Long id) {
        return false; //TODO
    }
}
