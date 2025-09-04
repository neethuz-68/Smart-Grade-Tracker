create database grade_tracker;

show databases;

 use grade_tracker;

  create table student (
    -> student_id INT AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(100) NOT NULL,
    -> email VARCHAR(100) UNIQUE NOT NULL,
    -> password VARCHAR(255) NOT NULL
    -> );

    create table semester (
    -> semester_id INT AUTO_INCREMENT PRIMARY KEY,
    -> student_id INT NOT NULL,
    -> semester_no INT NOT NULL,
    -> FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
    -> );

     create table subjects(
    -> subject_code INT AUTO_INCREMENT PRIMARY KEY,
    -> semester_id INT NOT NULL,
    -> subject_name VARCHAR(100) NOT NULL,
    -> credit FLOAT NOT NULL,
    -> FOREIGN KEY (semester_id) REFERENCES semester(semester_id) ON DELETE CASCADE
    -> );

SHOW TABLES;
DESCRIBE students;
DESCRIBE semester;
DESCRIBE subjects;


INSERT INTO students (name , email,password) VALUES ("Navaneeta","navaneeta@gmail.com","paswd0000");
INSERT INTO students (name , email,password) VALUES ("niya","niya@gmail.com","paswd1111");
INSERT INTO students (name , email,password) VALUES ("keerthana","keerthana@gmail.com","paswd2222");
INSERT INTO students (name , email,password) VALUES ("aparna","aparna@gmail.com","paswd3333");
 SELECT *FROM students;