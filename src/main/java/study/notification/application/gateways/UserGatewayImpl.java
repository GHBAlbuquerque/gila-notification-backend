package study.notification.application.gateways;

import study.notification.application.mappers.UserMapper;
import study.notification.domain.interfaces.gateways.UserGateway;
import study.notification.domain.entities.User;
import study.notification.domain.interfaces.repositories.UserRepository;
import study.notification.infrastructure.orm.UserORM;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserGatewayImpl implements UserGateway {

    private final UserRepository repository;

    public UserGatewayImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(Long id) {
        final Optional<UserORM> optional =  repository.findById(id);

        return optional.map(UserMapper::toDomain).orElse(null);
    }

    @Override
    public Map<Long, User> findAllById(List<Long> ids) {
        List<UserORM> usersORMs = repository.findAllById(ids);
        return usersORMs.stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toMap(User::getId, user -> user));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}