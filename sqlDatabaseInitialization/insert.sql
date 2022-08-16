INSERT INTO role VALUES ('admin'); 
INSERT INTO role VALUES ('leader'); 
INSERT INTO role VALUES ('member'); 

INSERT INTO groups (name) VALUES ('Managers'); 
INSERT INTO groups (name) VALUES ('Company'); 
INSERT INTO groups (name) VALUES ('Amazon'); 
INSERT INTO groups (name) VALUES ('Google'); 
INSERT INTO groups (name) VALUES ('Писатели'); 
    
-- Добавить админа
INSERT INTO users (name, email, password, status)
VALUES ('admin', 'admin@gmail.com', '63 -64 -89 -84 -16 -121 -11 73 -84 43 38 107 -81 -108 -72 -79 ',   'offline');

-- Добавить админа-компании
INSERT INTO users (name, email, password, status)
VALUES ('user', 'user2@gmail.com', '63 -64 -89 -84 -16 -121 -11 73 -84 43 38 107 -81 -108 -72 -79 ',  'offline');

-- Добавить заблокированного пользователя
INSERT INTO users (name, email, password, status)
VALUES ('user2', 'user@gmail.com', '63 -64 -89 -84 -16 -121 -11 73 -84 43 38 107 -81 -108 -72 -79 ',  'blocked');

-- Добавить пользователя
INSERT INTO users (name, email, password, status)
VALUES ('user3', 'user@gmail.com', '63 -64 -89 -84 -16 -121 -11 73 -84 43 38 107 -81 -108 -72 -79 ',  'offline');


-- Добавить админа
INSERT INTO user_group (user_id, group_id, role)
VALUES (1, 1,  'admin');

INSERT INTO user_group (user_id, group_id, role)
VALUES (2, 2,  'leader');

INSERT INTO user_group (user_id, group_id, role)
VALUES (3, 2,  'member');

INSERT INTO user_group (user_id, group_id, role)
VALUES (4, 5,  'leader');

INSERT INTO user_group (user_id, group_id, role)
VALUES (2, 3,  'member');

INSERT INTO user_group (user_id, group_id, role)
VALUES (1, 3,  'member');


INSERT INTO model (group_id, status)
VALUES (5, 'ready');


INSERT INTO author (name, group_id)
VALUES ('Айзек Азимов', 5);

INSERT INTO author (name, group_id)
VALUES ('Булгаков', 5);

INSERT INTO author (name, group_id)
VALUES ('Гоголь', 5);

INSERT INTO author (name, group_id)
VALUES ('Достоевский', 5);

INSERT INTO author (name, group_id)
VALUES ('Элиезер Юдковский', 5);


INSERT INTO files (author, name)
VALUES (1, 'Академия.txt');

INSERT INTO files (author, name)
VALUES (2, 'Мастер и Маргарита.txtt');

INSERT INTO files (author, name)
VALUES (3, 'Мертвые души.txt');

INSERT INTO files (author, name)
VALUES (4, 'Преступление и наказание.txt');

INSERT INTO files (author, name)
VALUES (5, 'Гарри Поттер и Методы Рационального Мышления - 1.txt');








