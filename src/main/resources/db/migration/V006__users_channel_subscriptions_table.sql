CREATE TABLE users_channel_subscriptions (
    channel_subscriptions_user_id BIGINT NOT NULL,
    channel_subscriptions_channel VARCHAR(50) NOT NULL,
    userorm_id BIGINT NOT NULL,

    PRIMARY KEY (channel_subscriptions_user_id, channel_subscriptions_channel),

    CONSTRAINT FK_channel_subscriptions
        FOREIGN KEY (channel_subscriptions_user_id, channel_subscriptions_channel)
        REFERENCES channel_subscriptions(user_id, channel),

    CONSTRAINT FK_channel_user_id
        FOREIGN KEY (userorm_id)
        REFERENCES users(id)
);