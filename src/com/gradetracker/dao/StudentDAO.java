package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Student;
import java.sql.*;

import javax.swing.JOptionPane;

public class StudentDAO {

public Student validateUser(String email, String password) {
    String sql = "SELECT student_id, name, email FROM Student WHERE email = ? AND password = ?";
    
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        System.out.println("Successfully connected to the database for validation.");
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"), password);
        }
    } catch (SQLException e) {
        // This will now show a detailed pop-up with the specific SQL error
        String errorMessage = "Database Error:\n" + e.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "SQL Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return null;
}
}

 /*   public Student validateUser(String email, String password) {
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
}*/