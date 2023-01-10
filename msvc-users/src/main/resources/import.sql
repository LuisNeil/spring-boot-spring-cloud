INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('lionel', '$2a$10$InZBK3yk6jT3lQgfPDfStucssza.OLChxoS8LXztcMApYOFL23dpu', 1, 'Lionel', 'Messi', 'lionel.messi@mail.com');
INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('neymar', '$2a$10$w4j7iUqL14A4X9O9dlW95OmCrbbfF9AuHT/YAT1nidY4zLDkGXZSq', 1, 'Neymar', 'DaSilva', 'neymar.dasilva@mail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);


