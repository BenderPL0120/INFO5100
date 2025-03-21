SHOW DATABASES;
-- create a customerdb database
CREATE DATABASE customerdb;
USE customerdb;
-- CREATE TABLES & COLUMNS
CREATE TABLE student(
student_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
college VARCHAR(50),
age INT,
hobbies VARCHAR(100)
);

-- displaying data present in the table (Read)
SELECT * FROM student;

-- Create:
INSERT INTO student(first_name, last_name, age, hobbies) VALUES("Rish", "K", 28,"Football, guitar");
INSERT INTO student(first_name, age, college, hobbies) VALUES ("Rish", 28, "Collegeof Engineering", "Basketball, guitar");

-- Update:
UPDATE student SET last_name = 'Kaushick' WHERE first_name = 'Rish';
UPDATE student SET last_name = 'Kaushick' WHERE student_id = 1;

-- Delete:
DELETE FROM student WHERE student_id = 1;

-- Delete the entire table
DROP TABLE studen