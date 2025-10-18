package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GradeDAOImpl implements GradeDAO {

    @Override
    public Map<String, Grade> getAllGrades() {
        Map<String, Grade> gradeMap = new HashMap<>();
        String sql = "SELECT * FROM Grade";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String letterGrade = rs.getString("grade");
                float gradePoint = rs.getFloat("grade_point");
                Grade grade = new Grade(letterGrade, gradePoint);
                gradeMap.put(letterGrade, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle more gracefully
        }
        return gradeMap;
    }
}