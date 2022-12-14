CREATE TABLE role
(
    name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE groups
(
    id INT generated by default on null as identity PRIMARY KEY,
    name VARCHAR(100)
);

--???????? ??????? ?????????????
CREATE TABLE users
(
    id INT generated by default on null as identity PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR(100),    
    email VARCHAR(100),
    status VARCHAR(30)
);

CREATE TABLE user_group
(
    user_id INT,
    group_id INT,
    role VARCHAR(50),
    FOREIGN KEY (role)  REFERENCES role (name) ON DELETE CASCADE,
    FOREIGN KEY (group_id)  REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE author
(
    id INT generated by default on null as identity PRIMARY KEY,
    name VARCHAR(100),
    group_id INT,
    FOREIGN KEY (group_id)  REFERENCES groups (id) ON DELETE CASCADE
);

CREATE TABLE files
(
    id INT generated by default on null as identity PRIMARY KEY,
    author INT,
    name VARCHAR(200),
    FOREIGN KEY (author)  REFERENCES author (id) ON DELETE CASCADE
);

CREATE TABLE model
(
    id INT generated by default on null as identity PRIMARY KEY,
    group_id INT,
    status VARCHAR(100),
    FOREIGN KEY (group_id)  REFERENCES groups (id) ON DELETE CASCADE
);