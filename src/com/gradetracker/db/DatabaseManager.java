
package com.gradetracker.db;

import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import com.gradetracker.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all database operations for the Smart Grade Tracker using MySQL.
 */
public class DatabaseManager {

    // --- NEW: MySQL Connection Details ---
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/grade_tracker"; // Replace 'gradetracker_db' with your database name
    private static final String DB_USER = "root"; // Replace with your MySQL username (e.g., "root")
    private static final String DB_PASSWORD = "15N@va86"; // Replace with your MySQL password
    
    private Connection connection;

    /**
     * Establishes a connection to the MySQL database.
     * @return true if the connection is successful, false otherwise.
     */
    public boolean connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
            System.out.println("MySQL database connection established.");
            return true;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Please add it to your project's classpath.");
            return false;
        } catch (SQLException e) {
            System.err.println("Failed to connect to the MySQL database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the active database connection.
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("MySQL database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the MySQL database connection: " + e.getMessage());
        }
    }

    // --- NOTE: The SQL queries for methods like validateUser() and getStudentData() ---
    // --- remain the same as they use standard SQL that works on both SQLite and MySQL. ---

    /**
     * Validates user credentials against the database.
     * @param email The user's email.
     * @param password The user's password.
     * @return A Student object if credentials are valid, null otherwise.
     */
    public Student validateUser(String email, String password) {
        String sql = "SELECT student_id, name, email FROM Student WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"), password);
            }
        } catch (SQLException e) {
            System.err.println("Error validating user: " + e.getMessage());
        }
        return null;
    }
    public Student getStudentData(Student student) {
    String semestersSql = "SELECT * FROM Semester WHERE student_id = ?";
    
    // --- MODIFIED SQL QUERY ---
    // Join Subject and Grade tables to get all data at once
    String subjectsSql = "SELECT s.subject_id, s.subject_name, s.credit, g.grade_letter, g.grade_point " +
                       "FROM Subject s " +
                       "INNER JOIN Grade g ON s.subject_id = g.subject_id " +
                       "WHERE s.semester_id = ?";

    try (PreparedStatement semestersPstmt = connection.prepareStatement(semestersSql)) {
        semestersPstmt.setInt(1, student.getStudentId());
        ResultSet semestersRs = semestersPstmt.executeQuery();

        List<Semester> semesterList = new ArrayList<>();
        while (semestersRs.next()) {
            int semesterId = semestersRs.getInt("semester_id");
            int semesterNo = semestersRs.getInt("semester_no");
            Semester semester = new Semester(semesterId, semesterNo);

            try (PreparedStatement subjectsPstmt = connection.prepareStatement(subjectsSql)) {
                subjectsPstmt.setInt(1, semesterId);
                ResultSet subjectsRs = subjectsPstmt.executeQuery();
                
                List<Subject> subjectList = new ArrayList<>();
                while(subjectsRs.next()) {
                    
                    // --- CORRECTED CONSTRUCTOR CALL ---
                    // Now we have all the data needed for the 6-argument constructor
                    subjectList.add(new Subject(
                        subjectsRs.getInt("subject_id"),
                        subjectsRs.getString("subject_name"),
                        subjectsRs.getFloat("credit"),
                        0, // Marks are not in this query, defaulting to 0
                        subjectsRs.getString("grade_letter"),
                        subjectsRs.getFloat("grade_point")
                    ));
                }
                semester.setSubjects(subjectList);
            }
            semesterList.add(semester);
        }
        student.setSemesters(semesterList);

    } catch (SQLException e) {
        System.err.println("Error fetching student data: " + e.getMessage());
    }
    return student;
}
}