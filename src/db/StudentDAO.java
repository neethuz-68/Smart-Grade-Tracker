package db;

import models.Student;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    // login
    public Student authenticate(String name, String password) {
        String query = "SELECT * FROM student WHERE name=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    new ArrayList<>()  // semesters can be loaded later
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login failed
    }

    // register
    public boolean register(String name, String password) {
        String query = "INSERT INTO student (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, name + "@mail.com"); // placeholder email
            stmt.setString(3, password);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
