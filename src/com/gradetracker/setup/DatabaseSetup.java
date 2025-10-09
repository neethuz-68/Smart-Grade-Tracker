package com.gradetracker.setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/grade_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "15N@va86";//give ur password for mysql

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Please add it to your project's classpath.");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Successfully connected to the database.");

           System.out.println("Dropping existing tables (if they exist)...");
            stmt.execute("DROP TABLE IF EXISTS Enrollment;");
            stmt.execute("DROP TABLE IF EXISTS Grade;");
            stmt.execute("DROP TABLE IF EXISTS Subject;");
            stmt.execute("DROP TABLE IF EXISTS Semester;");
            stmt.execute("DROP TABLE IF EXISTS Student;");
            System.out.println("Old tables dropped.");
            System.out.println("Creating new tables...");

            //Student Table
            stmt.execute("CREATE TABLE Student (" +
                    "st_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(255) NOT NULL);");
            System.out.println("Created table: Student");

            // Subject Table
            stmt.execute("CREATE TABLE Subject (" +
                    "sub_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "subject_name VARCHAR(50) NOT NULL, " +
                    "credit INT NOT NULL);");
            System.out.println("Created table: Subject");
            
            // Grade Table (Lookup Table)
            stmt.execute("CREATE TABLE Grade (" +
                    "grade CHAR(2) PRIMARY KEY, " +
                    "grade_point FLOAT NOT NULL);");
            System.out.println("Created table: Grade");

            // Enrollment Table (Linking Table)
            stmt.execute("CREATE TABLE Enrollment (" +
                    "enrollment_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "st_id INT NOT NULL, " +
                    "sub_id INT NOT NULL, " +
                    "semester_no INT NOT NULL, " +
                    "grade CHAR(2) NOT NULL, " +
                    "FOREIGN KEY (st_id) REFERENCES Student(st_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (sub_id) REFERENCES Subject(sub_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (grade) REFERENCES Grade(grade) ON DELETE CASCADE);");
            System.out.println("Created table: Enrollment");

            System.out.println("\nAll tables created successfully!");

        } catch (SQLException e) {
            System.err.println("Database setup failed.");
            e.printStackTrace();
        }
    }
}