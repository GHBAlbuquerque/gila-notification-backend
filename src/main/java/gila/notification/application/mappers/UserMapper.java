package gila.notification.application.mappers;

import gila.notification.domain.entities.User;
import gila.notification.infrastructure.orm.UserORM;

public class UserMapper {

    public static User toDomain(UserORM orm) {
        return new User(orm.getId(), orm.getName(), orm.getEmail(), orm.getPhoneNumber());
    }

}
