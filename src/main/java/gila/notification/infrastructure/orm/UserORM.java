package gila.notification.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name= "users")
public class UserORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CategorySubscriptionORM> categorySubscriptions;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ChannelSubscriptionORM> channelSubscriptions;

    public UserORM() {
    }

    public UserORM(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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

    public List<CategorySubscriptionORM> getCategorySubscriptions() {
        return categorySubscriptions;
    }

    public void setCategorySubscriptions(List<CategorySubscriptionORM> categorySubscriptions) {
        this.categorySubscriptions = categorySubscriptions;
    }

    public List<ChannelSubscriptionORM> getChannelSubscriptions() {
        return channelSubscriptions;
    }

    public void setChannelSubscriptions(List<ChannelSubscriptionORM> channelSubscriptions) {
        this.channelSubscriptions = channelSubscriptions;
    }
}
