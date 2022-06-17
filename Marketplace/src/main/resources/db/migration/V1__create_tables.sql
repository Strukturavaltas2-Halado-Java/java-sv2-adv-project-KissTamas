CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE ads
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    category       ENUM('VEHICLE', 'PROPERTY', 'CLOTHING', 'ELECTRONICS', 'OTHER'),
    price          VARCHAR(255) NULL,
    place          VARCHAR(255) NULL,
    description    VARCHAR(255) NULL,
    user_id         BIGINT NULL,
    CONSTRAINT pk_teams PRIMARY KEY (id)
);

ALTER TABLE ads
    ADD CONSTRAINT FK_ADS_WITH_USERS FOREIGN KEY (user_id) REFERENCES users (id);