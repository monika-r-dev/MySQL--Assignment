CREATE DATABASE movie_booking;
USE movie_booking;

CREATE TABLE movies (
    movie_id INT PRIMARY KEY,
    movie_name VARCHAR(100),
    language VARCHAR(50),
    duration INT
);

CREATE TABLE theaters (
    theater_id INT PRIMARY KEY,
    theater_name VARCHAR(100),
    location VARCHAR(100)
);

CREATE TABLE shows (
    show_id INT PRIMARY KEY,
    movie_id INT,
    theater_id INT,
    show_time TIME
);

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY,
    show_id INT,
    customer_name VARCHAR(100),
    seat_number VARCHAR(10),
    booking_status VARCHAR(20)
);

INSERT INTO movies VALUES
(1, 'Coolie', 'Tamil', 168),
(2, 'Leo', 'Tamil', 164),
(3, 'Jawan', 'Hindi', 169);

INSERT INTO theaters VALUES
(101, 'PVR Cinemas', 'Chennai'),
(102, 'AGS Cinemas', 'Chennai'),
(103, 'Rohini Theatre', 'Chennai');

INSERT INTO shows VALUES
(1001, 1, 101, '10:00:00'),
(1002, 2, 102, '14:00:00'),
(1003, 3, 103, '18:00:00');

INSERT INTO bookings VALUES
(1, 1001, 'Monika', 'A1', 'BOOKED'),
(2, 1002, 'Kavya', 'B2', 'BOOKED');

SELECT * FROM movies;

SELECT * FROM theaters;

SELECT * FROM shows;

SELECT * FROM bookings;
