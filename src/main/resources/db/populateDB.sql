DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM DISHES;
DELETE FROM menu;
DELETE FROM RESTAURANTS;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (name) VALUES
  ('BK'),
  ('KFC');
INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003);

INSERT INTO menu (date, restaurant_id) VALUES
  (now(), 100000),
  ('2018-01-01', 100000),
  (now(), 100001),
  ('2018-01-01', 100001);

INSERT INTO dishes (menu_id, name, price) VALUES
  (100004, 'meat', 200),
  (100004, 'vine', 500),
  (100005, 'potato', 100),
  (100005, 'juice', 100),

  (100006, 'chicken', 200),
  (100006, 'beer', 500),
  (100007, 'bread', 100),
  (100007, 'milk', 100);

INSERT INTO VOTES (DATE, menu_id, USER_ID) VALUES
  (now(), 100004, 100002),
  ('2018-01-01', 100007, 100002),
  ('2018-01-01', 100005, 100003);