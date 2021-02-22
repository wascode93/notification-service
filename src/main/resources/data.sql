-- Users Data --
INSERT INTO public.app_user(email, mobile_number, token, lang)
VALUES ('waseem.mounir93@gmail.com', '+201278955766', 'APP_TOKEN_1', 'EN');

INSERT INTO public.app_user(email, mobile_number, token, lang)
VALUES ('waseem.m.salama@gmail.com', '+201278955766', 'APP_TOKEN_2', 'AR');

INSERT INTO public.app_user(email, mobile_number, token, lang)
VALUES ('waseem123_73@hotmail.com', '+201278955766', 'APP_TOKEN_3', 'FR');

-- SMS Notifications Data --
INSERT INTO notification(user_id, message, sent)
VALUES (1, 'SMS Notification #1', FALSE);

INSERT INTO sms_notification(id)
VALUES (1);

INSERT INTO notification(user_id, message, sent)
VALUES (2, 'SMS Notification #2', FALSE);

INSERT INTO sms_notification(id)
VALUES (2);

INSERT INTO notification(user_id, message, sent)
VALUES (3, 'SMS Notification #3', FALSE);

INSERT INTO sms_notification(id)
VALUES (3);

INSERT INTO notification(user_id, message, sent)
VALUES (1, 'SMS Notification #4', FALSE);

INSERT INTO sms_notification(id)
VALUES (4);

INSERT INTO notification(user_id, message, sent)
VALUES (2, 'SMS Notification #5', FALSE);

INSERT INTO sms_notification(id)
VALUES (5);

INSERT INTO notification(user_id, message, sent)
VALUES (3, 'SMS Notification #6', FALSE);

INSERT INTO sms_notification(id)
VALUES (6);

-- Email Notifications Data --
INSERT INTO notification(user_id, message, sent)
VALUES (1, 'Email Notification #1', FALSE);

INSERT INTO email_notification(id)
VALUES (7);

INSERT INTO notification(user_id, message, sent)
VALUES (2, 'Email Notification #2', FALSE);

INSERT INTO email_notification(id)
VALUES (8);

INSERT INTO notification(user_id, message, sent)
VALUES (3, 'Email Notification #3', FALSE);

INSERT INTO email_notification(id)
VALUES (9);

-- Push Notifications Data --
INSERT INTO notification(user_id, message, sent)
VALUES (1, 'Push Notification #1', TRUE);

INSERT INTO push_notification(id, read)
VALUES (10, FALSE);

INSERT INTO notification(user_id, message, sent)
VALUES (2, 'Push Notification #2', TRUE);

INSERT INTO push_notification(id, read)
VALUES (11, FALSE);

INSERT INTO notification(user_id, message, sent)
VALUES (3, 'Push Notification #3', TRUE);

INSERT INTO push_notification(id, read)
VALUES (12, FALSE);

INSERT INTO notification(user_id, message, sent)
VALUES (1, 'Push Notification #4', FALSE);

INSERT INTO push_notification(id, read)
VALUES (13, FALSE);

INSERT INTO notification(user_id, message, sent)
VALUES (2, 'Push Notification #5', FALSE);

INSERT INTO push_notification(id, read)
VALUES (14, FALSE);

INSERT INTO notification(user_id, message, sent)
VALUES (3, 'Push Notification #6', FALSE);

INSERT INTO push_notification(id, read)
VALUES (15, FALSE);

ALTER SEQUENCE notification_id_seq START WITH 16;
