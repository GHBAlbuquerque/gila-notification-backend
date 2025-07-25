INSERT INTO users (id, name, email, phone_number) VALUES
(3, 'Clara', 'clara@example.com', '555-9012'),
(4, 'David', 'david@example.com', '555-3456'),
(5, 'Eva', 'eva@example.com', '555-7890');

INSERT INTO category_subscriptions (user_id, category) VALUES
(3, 'MOVIES'),
(3, 'MUSIC'),
(4, 'TECH'),
(4, 'SPORTS'),
(5, 'FINANCE'),
(5, 'HEALTH');

INSERT INTO channel_subscriptions (user_id, channel) VALUES
(3, 'EMAIL'),
(3, 'SMS'),
(4, 'PUSH'),
(5, 'EMAIL'),
(5, 'PUSH'),
(5, 'SMS');