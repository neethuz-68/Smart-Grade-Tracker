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
    public Subject getSubjectByName(String subjectName) {
        String sql = "SELECT * FROM subject WHERE subject_name = ?";
        Subject subject = null;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subjectName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                subject = new Subject(
                    rs.getInt("sub_id"),
                    rs.getString("subject_name"),
                    rs.getInt("credit")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject(
                    rs.getInt("sub_id"),
                    rs.getString("subject_name"),
                    rs.getInt("credit")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
}