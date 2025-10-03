/*Prerequisites
Before running this code, make sure you have:

A MySQL server running.

An empty database created (e.g., named grade_tracker).

The MySQL Connector/J .jar file added to your project's classpath.

Updated the DB_USER and DB_PASSWORD variables in the code below to match your MySQL credentials. */

package com.gradetracker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A one-time setup class to initialize the database.
 * This will drop existing tables, create new ones, and insert sample data.
 */
public class DatabaseSetup {

    // --- IMPORTANT: UPDATE THESE DETAILS ---
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gradetracker_db";
    private static final String DB_USER = "root"; // Your MySQL username
    private static final String DB_PASSWORD = "your_password"; // Your MySQL password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Successfully connected to the database.");

            // Step 1: Drop existing tables in reverse order of dependency
            System.out.println("Dropping existing tables...");
            stmt.execute("DROP TABLE IF EXISTS Grade;");
            stmt.execute("DROP TABLE IF EXISTS Subject;");
            stmt.execute("DROP TABLE IF EXISTS Semester;");
            stmt.execute("DROP TABLE IF EXISTS Student;");
            System.out.println("Tables dropped.");

            // Step 2: Create new tables
            System.out.println("Creating new tables...");
            createTables(stmt);
            System.out.println("Tables created.");

            // Step 3: Insert sample data
            System.out.println("Inserting sample data...");
            insertData(stmt);
            System.out.println("Sample data inserted.");

            System.out.println("\nDatabase setup complete!");

        } catch (SQLException e) {
            System.err.println("Database setup failed.");
            e.printStackTrace();
        }
    }

    private static void createTables(Statement stmt) throws SQLException {
        // Student Table
        stmt.execute("CREATE TABLE Student (" +
                "student_id INT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL);");

        // Semester Table
        stmt.execute("CREATE TABLE Semester (" +
                "semester_id INT PRIMARY KEY, " +
                "student_id INT NOT NULL, " +
                "semester_no INT NOT NULL, " +
                "FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE);");

        // Subject Table
        stmt.execute("CREATE TABLE Subject (" +
                "subject_id INT PRIMARY KEY, " +
                "semester_id INT NOT NULL, " +
                "subject_name VARCHAR(100) NOT NULL, " +
                "credit FLOAT NOT NULL, " +
                "FOREIGN KEY (semester_id) REFERENCES Semester(semester_id) ON DELETE CASCADE);");
        
        // Grade Table
        stmt.execute("CREATE TABLE Grade (" +
                "grade_id INT PRIMARY KEY, " +
                "subject_id INT NOT NULL, " +
                "grade_letter VARCHAR(2) NOT NULL, " +
                "grade_point FLOAT NOT NULL, " +
                "FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE);");
    }

    private static void insertData(Statement stmt) throws SQLException {
        // Insert Students
        stmt.execute("INSERT INTO Student (student_id, name, email, password) VALUES (39, 'Aparna KM', 'aparna@example.com', 'pass039');");
        stmt.execute("INSERT INTO Student (student_id, name, email, password) VALUES (119, 'Niya Sabu', 'niya@example.com', 'pass119');");

        // Insert Semesters
        stmt.execute("INSERT INTO Semester (semester_id, student_id, semester_no) VALUES (1, 119, 1);");
        stmt.execute("INSERT INTO Semester (semester_id, student_id, semester_no) VALUES (2, 119, 2);");
        stmt.execute("INSERT INTO Semester (semester_id, student_id, semester_no) VALUES (3, 39, 1);");

        // Insert Subjects
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (101, 1, 'Object-Oriented Programming', 4);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (102, 1, 'Data Structures', 4);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (103, 1, 'Digital Systems', 3);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (104, 2, 'Database Management Systems', 4);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (105, 2, 'Operating Systems', 4);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (106, 3, 'Object-Oriented Programming', 4);");
        stmt.execute("INSERT INTO Subject (subject_id, semester_id, subject_name, credit) VALUES (107, 3, 'Calculus', 4);");

        // Insert Grades
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (201, 101, 'A+', 9);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (202, 102, 'O', 10);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (203, 103, 'A', 8);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (204, 104, 'O', 10);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (205, 105, 'B+', 7);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (206, 106, 'B', 6);");
        stmt.execute("INSERT INTO Grade (grade_id, subject_id, grade_letter, grade_point) VALUES (207, 107, 'C', 5);");
    }
}
