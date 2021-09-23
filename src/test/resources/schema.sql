DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    email      VARCHAR(250) DEFAULT NULL
);
