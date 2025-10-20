package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student validateUser(String email, String password) {
        String query = "SELECT st_id, name, email FROM student WHERE email = ? AND password = ?";
        
        // Use the central DatabaseManager to get a connection
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
        // --- THIS IS THE FIX ---
        // Pass the password to the constructor
        return new Student(
            rs.getInt("st_id"),
            rs.getString("name"),
            rs.getString("email"),
            password // <-- The missing argument
        );
    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // If user is not found or an error occurs, return null
        return null;
    }

    @Override
    public boolean createStudent(Student student) {
        String query = "INSERT INTO student (name, email, password) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword()); // Note: Passwords should be hashed in a real app
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}