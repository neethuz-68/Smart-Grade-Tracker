CREATE DATABASE IF NOT EXISTS grade_tracker;
USE grade_tracker;

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE semester (
    semester_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    semester_no INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

CREATE TABLE subjects (
    subject_code INT AUTO_INCREMENT PRIMARY KEY,
    semester_id INT NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    credit FLOAT NOT NULL,
    FOREIGN KEY (semester_id) REFERENCES semester(semester_id) ON DELETE CASCADE
);
