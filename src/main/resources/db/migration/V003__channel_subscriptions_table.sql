CREATE TABLE channel_subscriptions (
    user_id BIGINT,
    channel VARCHAR(50),

    PRIMARY KEY(user_id, channel),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE INDEX idx_channel_subscriptions_category ON channel_subscriptions(channel);