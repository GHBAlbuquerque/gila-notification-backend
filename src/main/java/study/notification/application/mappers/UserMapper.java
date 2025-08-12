package study.notification.application.mappers;

import study.notification.domain.entities.User;
import study.notification.infrastructure.orm.UserORM;

public class UserMapper {

    public static User toDomain(UserORM orm) {
        return new User(orm.getId(), orm.getName(), orm.getEmail(), orm.getPhoneNumber());
    }

}
