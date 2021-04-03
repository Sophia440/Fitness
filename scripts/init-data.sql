USE fitness_db;

INSERT INTO user
(id, login, password, role) 
VALUES 
(1, "admin", "admin", 'ADMIN'),
(2, "client1", "client", 'CLIENT'),  
(3, "instructor1", "instructor", 'INSTRUCTOR'),
(4, "client2", "client", 'CLIENT'),
(5, "instructor2", "instructor", 'INSTRUCTOR');
-- MD5("admin")

INSERT INTO service 
(id, name, price) 
VALUES 
(1, "Create a personalized meal plan", 20.00), 
(2, "Create a personalized program", "20.00"), 
(3, "1 month membership", "30.00"), 
(4, "3 months membership", "85.00"), 
(5, "6 months membership", "160.00"), 
(6, "12 months membership", "300.00");

INSERT INTO membership
(id, client_id, start_date, end_date) 
VALUES 
(1, 2, "2021-05-01", "2021-06-01"),
(2, 4, "2021-04-01", "2021-07-01");

INSERT INTO program
(id, client_id, instructor_id, status) 
VALUES 
(1, 2, 3, 'ACTIVE'), 
(2, 4, 5, 'AWAITING_CLIENT_ANSWER');

INSERT INTO exercise
(id, name) 
VALUES 
(1, "Warm-up"), 
(2, "Final stretching"), 
(3, "Treadmill, low speed, 10 minutes"), 
(4, "Treadmill, low speed, 30 minutes"), 
(5, "Treadmill, medium speed, 10 minutes"), 
(6, "Treadmill, medium speed, 30 minutes"), 
(7, "Treadmill, high speed, 10 minutes"),
(8, "Treadmill, high speed, 30 minutes"),
(9, "Bodyweight squats, 10"),
(10, "Push-ups (on knees or regular), 10"),
(11, "One arm dumbbell rows (10 per arm)"),
(12, "Goblet squats, 10"),
(13, "Barbell squats, 10"),
(14, "Barbell rows, 10"),
(15, "Inverted barbell rows, 10"),
(16, "Barbell Romanian deadlifts, 5"),
(17, "Pull-ups, 10");

INSERT INTO assigned_service
(id, service_id, client_id, status) 
VALUES 
(1, 1, 2, 'PAYMENT_RECEIVED'), 
(2, 2, 2, 'PAYMENT_RECEIVED'), 
(3, 3, 2, 'PAYMENT_RECEIVED'), 
(4, 1, 4, 'AWAITING_PAYMENT'), 
(5, 2, 4, 'AWAITING_PAYMENT'), 
(6, 4, 4, 'PAYMENT_RECEIVED');

INSERT INTO assigned_exercise 
(exercise_id, program_id) 
VALUES 
(1, 1), 
(3, 1), 
(9, 1), 
(10, 1), 
(11, 1), 
(2, 1), 
(1, 2),
(3, 2),
(13, 2),
(10, 2),
(15, 2),
(2, 2);