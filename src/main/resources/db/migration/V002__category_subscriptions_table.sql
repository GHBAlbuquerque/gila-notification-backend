CREATE TABLE category_subscriptions (
    user_id BIGINT,
    category VARCHAR(50),

    PRIMARY KEY(user_id, category),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE INDEX idx_category_subscriptions_category ON category_subscriptions(category);
