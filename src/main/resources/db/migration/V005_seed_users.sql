INSERT INTO users (id, name, email, phone_number) VALUES
(1, 'Alice', 'alice@example.com', '555-1234'),
(2, 'Bob', 'bob@example.com', '555-5678');

INSERT INTO category_subscriptions (user_id, category) VALUES
(1, 'SPORTS'),
(1, 'MOVIES'),
(2, 'FINANCE');

INSERT INTO channel_subscriptions (user_id, channel) VALUES
(1, 'EMAIL'),
(1, 'PUSH'),
(2, 'SMS');