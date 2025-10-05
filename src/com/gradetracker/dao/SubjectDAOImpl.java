package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public List<Subject> getSubjectsForSemester(int semesterId) {
        List<Subject> subjects = new ArrayList<>();
        // This query joins Subject and Grade to get all necessary info
        String sql = "SELECT s.subject_id, s.subject_name, s.credit, g.grade_letter, g.grade_point " +
                     "FROM Subject s JOIN Grade g ON s.subject_id = g.subject_id " +
                     "WHERE s.semester_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, semesterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                subjects.add(new Subject(
                    rs.getInt("subject_id"),
                    rs.getString("subject_name"),
                    rs.getFloat("credit"),
                    0, // Marks are not stored/retrieved in this query
                    rs.getString("grade_letter"),
                    rs.getFloat("grade_point")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle more gracefully
        }
        return subjects;
    }
}