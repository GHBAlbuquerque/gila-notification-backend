package gila.notification.infrastructure.orm;

import gila.notification.domain.entities.CategorySubscription;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "users")
public class UserORM {

    @Id
    private Long id;

    private String name;

    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany
    private List<CategorySubscription> categorySubscriptions;

    @OneToMany
    private List<ChannelSubscriptionORM> channelSubscriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CategorySubscription> getCategorySubscriptions() {
        return categorySubscriptions;
    }

    public void setCategorySubscriptions(List<CategorySubscription> categorySubscriptions) {
        this.categorySubscriptions = categorySubscriptions;
    }

    public List<ChannelSubscriptionORM> getChannelSubscriptions() {
        return channelSubscriptions;
    }

    public void setChannelSubscriptions(List<ChannelSubscriptionORM> channelSubscriptions) {
        this.channelSubscriptions = channelSubscriptions;
    }
}
