CREATE TABLE notification_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    category VARCHAR(50),
    channel VARCHAR(50),
    message TEXT,
    timestamp TIMESTAMP,
    status VARCHAR(20),

    FOREIGN KEY (user_id) REFERENCES users(id)
)