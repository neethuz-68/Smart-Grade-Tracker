-- Insert sample students
INSERT INTO students (name, email, password) VALUES
("Navaneeta", "navaneeta@gmail.com", "paswd0000"),
("Niya", "niya@gmail.com", "paswd1111"),
("Keerthana", "keerthana@gmail.com", "paswd2222"),
("Aparna", "aparna@gmail.com", "paswd3333");

-- Insert sample semester records
INSERT INTO semester (student_id, semester_no) VALUES
(1, 1),
(1, 2),
(2, 1);

-- Insert sample subjects
INSERT INTO subjects (semester_id, subject_name, credit) VALUES
(1, "Maths", 4),
(1, "Physics", 3),
(2, "Chemistry", 4),
(3, "Biology", 3);
