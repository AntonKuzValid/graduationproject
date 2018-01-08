DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu;
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
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
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

CREATE TABLE menu
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date          DATE    NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_date_restaurant_idx
  ON menu (date, restaurant_id);

CREATE TABLE dishes
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id INTEGER      NOT NULL,
  name    VARCHAR(255) NOT NULL,
  price   INTEGER      NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu (id)
  ON DELETE CASCADE
);

CREATE TABLE votes
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date    DATE    NOT NULL,
  menu_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu (id),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
);
CREATE UNIQUE INDEX vote_unique_date_user_idx ON votes(date,user_id)