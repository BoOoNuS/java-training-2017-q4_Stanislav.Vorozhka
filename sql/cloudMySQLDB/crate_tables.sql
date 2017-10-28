-- Drop all tables --
DROP TABLE orders;
DROP TABLE cars;
DROP TABLE users;
DROP TABLE states;
DROP TABLE penalty;
DROP TABLE marks;
DROP TABLE classes;
DROP TABLE roles;

-- Create tables --
-- Create table roles --
CREATE TABLE roles (
  id INT AUTO_INCREMENT NOT NULL,
  name CHARACTER VARYING(8) NOT NULL ,
  CONSTRAINT role_id PRIMARY KEY (id)
);

-- Create table users --
CREATE TABLE users (
  login CHARACTER VARYING(16) NOT NULL,
  password CHARACTER VARYING(32) NOT NULL,
  full_name CHARACTER VARYING(32) NOT NULL,
  passport CHARACTER VARYING(8) NULL,
  blocked BIT NOT NULL DEFAULT 0,
  role_id INTEGER NOT NULL DEFAULT 1,
  CONSTRAINT user_login PRIMARY KEY (login),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create table marks --
CREATE TABLE marks (
  id INT AUTO_INCREMENT NOT NULL,
  name CHARACTER VARYING(12) NOT NULL ,
  CONSTRAINT mark_id PRIMARY KEY (id)
);

-- Create table classes --
CREATE TABLE classes (
  id INT AUTO_INCREMENT NOT NULL,
  name CHARACTER VARYING(10) NOT NULL,
  CONSTRAINT class_id PRIMARY KEY (id)
);

-- Create table cars --
CREATE TABLE cars (
  id INT AUTO_INCREMENT NOT NULL,
  mark_id INTEGER NOT NULL,
  class_id INTEGER NOT NULL,
  name CHARACTER VARYING(16) NOT NULL,
  cost INTEGER NOT NULL,
  there_is BIT NOT NULL DEFAULT 0,
  CONSTRAINT car_id PRIMARY KEY (id),
  FOREIGN KEY (mark_id) REFERENCES marks(id),
  FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- Create table states --
CREATE TABLE states (
  id INT AUTO_INCREMENT NOT NULL,
  name CHARACTER VARYING(12) NOT NULL,
  CONSTRAINT state_id PRIMARY KEY (id)
);
-- Create table penalty --
CREATE TABLE penalty (
  id INT AUTO_INCREMENT NOT NULL,
  cost INTEGER NULL,
  cause CHARACTER VARYING(96) NOT NULL,
  CONSTRAINT penalty_id PRIMARY KEY (id)
);

-- Create table orders --
CREATE TABLE orders (
  number INT AUTO_INCREMENT NOT NULL,
  user_login CHARACTER VARYING(16) NOT NULL,
  car_id INTEGER NOT NULL,
  state_id INTEGER NOT NULL DEFAULT 1,
  penalty_id INTEGER NULL,
  driver BOOLEAN NOT NULL DEFAULT 0,
  term DATE NOT NULL,
  CONSTRAINT order_number PRIMARY KEY (number),
  FOREIGN KEY (user_login) REFERENCES users(login),
  FOREIGN KEY (car_id) REFERENCES cars(id),
  FOREIGN KEY (state_id) REFERENCES states(id),
  FOREIGN KEY (penalty_id) REFERENCES penalty(id)
)