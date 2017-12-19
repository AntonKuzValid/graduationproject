DELETE FROM user_roles;
DELETE  FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (name) VALUES
  ('BK'),
  ('KFC');
INSERT INTO users (name, email, password,restaurant_id) VALUES
  ('User', 'user@yandex.ru', 'password',100000),
  ('Admin', 'admin@gmail.com', 'admin', 100001);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003);

INSERT INTO dishes (restaurant_id, name, price) VALUES
  (100000,'meat',200),
  (100001,'potatoe',100);

INSERT INTO VOTES (DATE, RESTAURANT_ID, USER_ID) VALUES
  ('2017-10-30',100000,100002);