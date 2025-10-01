package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Student;
import java.sql.*;

public class StudentDAO {

    public Student validateUser(String email, String password) {
        String sql = "SELECT student_id, name, email FROM Student WHERE email = ? AND password = ?";
        
        // Use the new DatabaseManager to get a connection
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"), password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // You would add other methods like getStudentById(), createStudent(), etc. here.
}