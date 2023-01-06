INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('lionel', '$2a$10$BhrbwEofgPbrAwpPx2jH6OnaiiSNm.xf.jfNa8katMUNdg8MyOEiu', 1, 'Lionel', 'Messi', 'lionel.messi@mail.com');
INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('neymar', '$2a$10$cGdAn/SBmJ7uVH4fQHIii.4wedEe8zvxsYAGtWf0xTapgGKsHgLQ2', 1, 'Neymar', 'DaSilva', 'neymar.dasilva@mail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);


