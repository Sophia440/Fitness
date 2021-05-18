USE fitness_db;

INSERT INTO user
(id, login, password, role, discount) 
VALUES 
(1, 'admin', 'admin', 'ADMIN', NULL),
(2, 'client1', 'client', 'CLIENT', 20),  
(3, 'instructor1', 'instructor', 'INSTRUCTOR', NULL),
(4, 'client2', 'client', 'CLIENT', 25),
(5, 'instructor2', 'instructor', 'INSTRUCTOR', NULL),
(6, 'client3', 'client', 'CLIENT', 0);
-- MD5('admin')

INSERT INTO membership
(id, client_id, start_date, end_date, payment_date) 
VALUES 
(1, 2, '2021-05-01', '2021-06-01', '2021-05-01'),
(2, 4, '2021-04-01', '2021-07-01', '2021-04-01');

INSERT INTO program
(id, client_id, instructor_id, start_date, end_date, status) 
VALUES 
(1, 2, 3, '2021-05-01', '2021-06-01', 'AWAITING_CLIENT_ANSWER'), 
(2, 4, 5, '2021-04-01', '2021-07-01', 'ACTIVE');

INSERT INTO exercise
(id, name) 
VALUES 
(1, 'Warm-up'), 
(2, 'Final stretching'), 
(3, 'Treadmill, low speed, 10 minutes'), 
(4, 'Treadmill, low speed, 30 minutes'), 
(5, 'Treadmill, medium speed, 10 minutes'), 
(6, 'Treadmill, medium speed, 30 minutes'), 
(7, 'Treadmill, high speed, 10 minutes'),
(8, 'Treadmill, high speed, 30 minutes'),
(9, 'Bodyweight squats, 10'),
(10, 'Push-ups (on knees or regular), 10'),
(11, 'One arm dumbbell rows (10 per arm)'),
(12, 'Goblet squats, 10'),
(13, 'Barbell squats, 10'),
(14, 'Barbell rows, 10'),
(15, 'Inverted barbell rows, 10'),
(16, 'Barbell Romanian deadlifts, 5'),
(17, 'Pull-ups, 10');

INSERT INTO assigned_exercise 
(program_id, exercise_id) 
VALUES 
(1, 1), 
(1, 3), 
(1, 9), 
(1, 10), 
(1, 11), 
(1, 2), 
(2, 1),
(2, 3),
(2, 13),
(2, 10),
(2, 15),
(2, 2);

INSERT INTO diet 
(id, client_id, instructor_id, start_date, end_date, status) 
VALUES 
(1, 2, 3, '2021-05-01', '2021-06-01', 'AWAITING_CLIENT_ANSWER'), 
(2, 4, 5, '2021-04-01', '2021-07-01', 'ACTIVE');

INSERT INTO dish 
(id, name, meal) 
VALUES 
(1, 'Oatmeal', 'BREAKFAST'), 
(2, 'Omelette', 'BREAKFAST'), 
(3, 'Greek yogurt', 'BREAKFAST'), 
(4, 'Fruit snack', 'SNACK'), 
(5, 'Protein shake', 'SNACK'), 
(6, 'Vegetable salad', 'SNACK'), 
(7, 'Steamed vegetables', 'LUNCH'),
(8, 'Baked chicken breasts', 'DINNER'),
(9, 'Brown rice', 'LUNCH'),
(10, 'Boiled chicken', 'LUNCH'),
(11, 'Roasted beef', 'DINNER'),
(12, 'Sauteed broccoli', 'DINNER'),
(13, 'Mixed nuts', 'SNACK'),
(14, 'Tuna salad', 'DINNER'),
(15, 'Baked fish', 'LUNCH');

INSERT INTO assigned_dish
(diet_id, dish_id) 
VALUES 
(1, 2), 
(1, 3), 
(1, 4), 
(1, 8), 
(1, 9), 
(1, 13), 
(1, 6), 
(1, 15), 
(2, 1),
(2, 5),
(2, 4),
(2, 7),
(2, 11),
(2, 14),
(2, 10),
(2, 12);