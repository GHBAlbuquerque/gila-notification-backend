package gila.notification.application.mappers;

import gila.notification.domain.entities.User;
import gila.notification.infrastructure.orm.UserORM;

public class UserMapper {

    public static User toDomain(UserORM orm) {
        return new User(orm.getId(), orm.getName(), orm.getEmail(), orm.getPhoneNumber());
    }

    public static UserORM toOrm(User user) {
        UserORM orm = new UserORM();
        orm.setId(user.getId());
        orm.setName(user.getName());
        orm.setEmail(user.getEmail());
        orm.setPhoneNumber(user.getPhoneNumber());
        return orm;
    }
}
