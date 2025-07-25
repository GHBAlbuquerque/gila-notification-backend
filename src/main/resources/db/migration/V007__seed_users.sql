INSERT INTO users (id, name, email, phone_number) VALUES
(1, 'Alice', 'alice@example.com', '555-1234'),
(2, 'Bob', 'bob@example.com', '555-5678'),
(3, 'Clara', 'clara@example.com', '555-9012'),
(4, 'David', 'david@example.com', '555-3456'),
(5, 'Eva', 'eva@example.com', '555-7890');

INSERT INTO category_subscriptions (user_id, category) VALUES
(1, 'SPORTS'),
(1, 'MOVIES'),
(2, 'FINANCE'),
(3, 'MOVIES'),
(3, 'SPORTS'),
(4, 'SPORTS'),
(5, 'FINANCE');

INSERT INTO channel_subscriptions (user_id, channel) VALUES
(1, 'SMS'),
(1, 'PUSH'),
(2, 'EMAIL'),
(3, 'EMAIL'),
(3, 'SMS'),
(4, 'PUSH'),
(5, 'EMAIL'),
(5, 'PUSH'),
(5, 'SMS');