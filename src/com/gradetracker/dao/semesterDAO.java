package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Semester;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAO {

    public boolean createSemester(Semester semester) {
        String sql = "INSERT INTO semester(student_id, semester_no) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, semester.getStudentId());
            pstmt.setInt(2, semester.getSemesterNo());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Semester getSemesterById(int id) {
        String sql = "SELECT * FROM semester WHERE semester_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Semester(rs.getInt("semester_id"),
                                    rs.getInt("student_id"),
                                    rs.getInt("semester_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Semester> getSemestersByStudent(int studentId) {
        List<Semester> list = new ArrayList<>();
        String sql = "SELECT * FROM semester WHERE student_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Semester(rs.getInt("semester_id"),
                                      rs.getInt("student_id"),
                                      rs.getInt("semester_no")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
