package gila.notification.application.gateways;

import gila.notification.application.mappers.UserMapper;
import gila.notification.domain.interfaces.gateways.UserGateway;
import gila.notification.domain.entities.User;
import gila.notification.domain.interfaces.repositories.UserRepository;

import java.util.Optional;

public class UserGatewayImpl implements UserGateway {

    private final UserRepository repository;

    public UserGatewayImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}