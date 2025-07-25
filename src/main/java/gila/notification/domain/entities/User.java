package gila.notification.domain.entities;

import java.util.Objects;

public class User {
    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public User(Long id, String name, String email, String phoneNumber) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}