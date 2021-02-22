-- CREATE DATABASE notification OWNER postgres;

DROP TABLE IF EXISTS push_notification;
DROP TABLE IF EXISTS sms_notification;
DROP TABLE IF EXISTS email_notification;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user
(
    id            BIGSERIAL    NOT NULL PRIMARY KEY,
    email         VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(255) NOT NULL,
    token         VARCHAR(255) NOT NULL,
    lang          VARCHAR(2)   NOT NULL
);

CREATE TABLE notification
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    user_id INT8      NOT NULL REFERENCES app_user (id),
    message TEXT      NOT NULL,
    sent    BOOLEAN   NOT NULL DEFAULT FALSE
);

CREATE TABLE email_notification
(
    id INT8 REFERENCES notification (id) PRIMARY KEY
);

CREATE TABLE sms_notification
(
    id INT8 REFERENCES notification (id) PRIMARY KEY
);

CREATE TABLE push_notification
(
    id   INT8 REFERENCES notification (id) PRIMARY KEY,
    read BOOLEAN NOT NULL DEFAULT FALSE
);