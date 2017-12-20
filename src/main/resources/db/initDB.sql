DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE restaurants
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255) NOT NULL
);
CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
  ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER      NOT NULL,
  name          VARCHAR(255) NOT NULL,
  price         INTEGER      NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
  ON DELETE CASCADE
);
CREATE UNIQUE INDEX dishes_unique_restaurant_idx
  ON dishes (restaurant_id);

CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date          DATE    NOT NULL,
  restaurant_id INTEGER NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
);
CREATE UNIQUE INDEX votes_unique_date_user_idx
  ON votes (date, user_id);