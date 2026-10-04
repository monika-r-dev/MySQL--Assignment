CREATE DATABASE student_management;
USE student_management;

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(50)
);

CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_id INT
);

CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY,
    student_id INT,
    attendance_percentage DOUBLE
);

CREATE TABLE marks (
    mark_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    marks INT
);
INSERT INTO students VALUES
(1, 'Monika', 'monika@gmail.com'),
(2, 'Kavya', 'kavya@gmail.com'),
(3, 'Priya', 'priya@gmail.com');

INSERT INTO courses VALUES
(101, 'Java'),
(102, 'SQL'),
(103, 'JDBC');

INSERT INTO enrollments VALUES
(1, 1, 101),
(2, 1, 102),
(3, 2, 101),
(4, 3, 103);

INSERT INTO attendance VALUES
(1, 1, 85.5),
(2, 2, 78.0),
(3, 3, 92.5);

INSERT INTO marks VALUES
(1, 1, 101, 85),
(2, 1, 102, 90),
(3, 2, 101, 75),
(4, 3, 103, 88);

SELECT * FROM students;
SELECT * FROM courses;
SELECT * FROM enrollments;
SELECT * FROM attendance;
SELECT * FROM marks;

SELECT
    s.student_id,
    s.student_name,
    c.course_name,
    m.marks,
    CASE
        WHEN m.marks >= 50 THEN 'PASS'
        ELSE 'FAIL'
    END AS result
FROM students s
JOIN marks m
    ON s.student_id = m.student_id
JOIN courses c
    ON m.course_id = c.course_id
 