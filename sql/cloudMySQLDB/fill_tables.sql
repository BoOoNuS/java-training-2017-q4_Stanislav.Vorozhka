-- Fill DB --
-- Fill table classes --
INSERT INTO classes (name) VALUES ('Business');
INSERT INTO classes (name) VALUES ('Sport');
INSERT INTO classes (name) VALUES ('Economy');
INSERT INTO classes (name) VALUES ('Medium');

-- Fill table states --
INSERT INTO states (name) VALUES ('Expectation');
INSERT INTO states (name) VALUES ('Rejected');
INSERT INTO states (name) VALUES ('Confirmed');
INSERT INTO states (name) VALUES ('Paid');
INSERT INTO states (name) VALUES ('Completed');

-- Fill table marks --
INSERT INTO marks (name) VALUES ('Audi');
INSERT INTO marks (name) VALUES ('BMW');
INSERT INTO marks (name) VALUES ('Peugeot');
INSERT INTO marks (name) VALUES ('Hyundai');
INSERT INTO marks (name) VALUES ('KIA');
INSERT INTO marks (name) VALUES ('Lexus');
INSERT INTO marks (name) VALUES ('Mazda');
INSERT INTO marks (name) VALUES ('Nisan');
INSERT INTO marks (name) VALUES ('Opel');
INSERT INTO marks (name) VALUES ('Renault');
INSERT INTO marks (name) VALUES ('Porsche');
INSERT INTO marks (name) VALUES ('Mercedes');
INSERT INTO marks (name) VALUES ('Mitsubishi');

-- Fill table roles --
INSERT INTO roles (name) VALUES ('Client');
INSERT INTO roles (name) VALUES ('Manager');
INSERT INTO roles (name) VALUES ('Admin');

-- Fill table users --
INSERT INTO users (login, password, full_name, role_id, passport)
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'Stanislav Vorozhka', 3, 'AA000001');

INSERT INTO users (login, password, full_name, role_id, passport)
VALUES ('manager', '1d0258c2440a8d19e716292b231e3190', 'Petya Petrov', 2, 'AA000002');

INSERT INTO users (login, password, full_name, passport)
VALUES ('client', '62608e08adc29a8d6dbc9754e659f125', 'Styopa Styepanov', 'AA000003');

-- Fill table cars --
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (1, 4, 'A4', 1800);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (2, 2, 'i8', 10040);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (3, 3, '607', 1028);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (4, 3, 'Accent', 909);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (5, 3, 'Soul', 909);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (6, 1, 'LS 460 LAWD', 7082);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (7, 4, '6', 1324);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (8, 2, 'GT-R', 6699);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (9, 3, 'ENJOY 1,4/AT', 877);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (10, 3, 'Kaptur', 660);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (11, 2, '911 Turbo', 11947);
INSERT INTO cars (mark_id, class_id, name, cost) VALUES (12, 1, 'Maybach', 7820);
INSERT INTO cars (mark_id, class_id, name, cost, there_is) VALUES (13, 2, 'Outlander', 1399, 0);

-- Fill table penalty --
INSERT INTO penalty (cost, cause) VALUES (0, 'Your had crashed us car, and today we reject you order');

-- Fill table orders --
INSERT INTO orders (user_login, car_id, state_id, driver, term) VALUES ('admin', 11, 4, 1, '2017-05-19');
INSERT INTO orders (user_login, car_id, state_id, penalty_id, term) VALUES ('client', 2, 2, 1, '2017-05-20');
INSERT INTO orders (user_login, car_id, state_id, term) VALUES ('manager', 4, 1, '2017-05-30');

