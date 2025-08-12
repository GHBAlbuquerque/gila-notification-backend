package study.notification.application.gateways;

import study.notification.application.exceptions.NotificationNotFoundException;
import study.notification.application.mappers.NotificationMapper;
import study.notification.domain.enums.NotificationStatus;
import study.notification.domain.interfaces.gateways.NotificationGateway;
import study.notification.domain.entities.Notification;
import study.notification.domain.interfaces.repositories.NotificationRepository;
import study.notification.infrastructure.orm.NotificationORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NotificationGatewayImpl implements NotificationGateway {

    private final NotificationRepository repository;

    public NotificationGatewayImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification create(Notification notification) {
        NotificationORM entity = NotificationMapper.toOrm(notification);
        NotificationORM saved = repository.save(entity);
        return NotificationMapper.toDomain(saved);
    }

    @Override
    public Notification findById(Long id) {
        return repository.findById(id)
                .map(NotificationMapper::toDomain)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id " + id));
    }

    @Override
    public Notification updateStatus(Long id, NotificationStatus status) {
        Notification entity = findById(id);
        entity.setStatus(status);

        NotificationORM updated = repository.save(NotificationMapper.toOrm(entity));
        return NotificationMapper.toDomain(updated);
    }

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(NotificationMapper::toDomain);
    }
}
