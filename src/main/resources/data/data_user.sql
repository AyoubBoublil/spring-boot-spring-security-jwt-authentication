-- init table role
INSERT INTO app_role (role) VALUES ('ADMIN');
INSERT INTO app_role (role) VALUES ('USER');

-- init table user
INSERT INTO app_user (actived, username, password) VALUES (true, 'ayoub', '$2a$10$ix0O52OuFPlHfl1ReHgQleDeehY/xGUhk849U6mMPxLdNJdNJwJOC');

-- init table user_roles
INSERT INTO app_user_roles (app_user_id, roles_id) VALUES (1,1);
INSERT INTO app_user_roles (app_user_id, roles_id) VALUES (1,2);
