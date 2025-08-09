CREATE TABLE users_category_subscriptions (
    category_subscriptions_user_id BIGINT NOT NULL,
    category_subscriptions_category VARCHAR(50) NOT NULL,
    userorm_id BIGINT NOT NULL,

    PRIMARY KEY (category_subscriptions_user_id, category_subscriptions_category),

    CONSTRAINT FK_category_subscriptions
        FOREIGN KEY (category_subscriptions_user_id, category_subscriptions_category)
        REFERENCES category_subscriptions(user_id, category),

    CONSTRAINT FK_category_user_id
        FOREIGN KEY (userorm_id)
        REFERENCES users(id)
);

CREATE INDEX idx_users_category_subscriptions_userorm_id ON users_category_subscriptions(userorm_id);