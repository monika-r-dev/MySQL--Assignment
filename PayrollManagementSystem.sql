CREATE DATABASE payroll_management;

USE payroll_management;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(50),
    basic_salary DOUBLE
);

CREATE TABLE salaries (
    salary_id INT PRIMARY KEY,
    employee_id INT,
    basic_salary DOUBLE,
    allowance DOUBLE,
    deduction DOUBLE
);

CREATE TABLE payroll (
    payroll_id INT PRIMARY KEY,
    employee_id INT,
    net_salary DOUBLE,
    tax DOUBLE,
    pay_month VARCHAR(20)
);

INSERT INTO employees VALUES
(1, 'Monika', 'IT', 40000),
(2, 'Kavya', 'HR', 35000),
(3, 'Priya', 'Finance', 45000);

INSERT INTO salaries VALUES
(101, 1, 40000, 5000, 2000),
(102, 2, 35000, 4000, 1500),
(103, 3, 45000, 6000, 2500);

INSERT INTO payroll VALUES
(1001, 1, 43000, 2000, 'October'),
(1002, 2, 37500, 1500, 'October'),
(1003, 3, 48500, 2500, 'October');

SELECT * FROM employees;

SELECT * FROM salaries;

SELECT * FROM payroll;