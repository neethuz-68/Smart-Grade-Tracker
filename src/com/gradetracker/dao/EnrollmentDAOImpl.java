
 package com.gradecalculator.dao;

import com.gradecalculator.dao.EnrollmentDAO;
import com.gradecalculator.db.DatabaseManager; // Assuming your connection manager
import com.gradecalculator.models.Enrollment;
import com.gradecalculator.models.Grade;
import com.gradecalculator.models.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public boolean createEnrollment(Enrollment enrollment) {
        // SQL to insert into your specific 'enrollment' table
        String sql = "INSERT INTO enrollment (st_id, sub_id, semester_no, grade) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollment.getStId()); // Assumes Enrollment model has getStId()
            pstmt.setInt(2, enrollment.getSubject().getSubId());
            pstmt.setInt(3, enrollment.getSemesterNo());
            pstmt.setString(4, enrollment.getGrade().getLetterGrade());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        // This single JOIN query gets all the data needed to build the objects
        String sql = "SELECT e.enrollment_id, e.semester_no, " +
                       "s.sub_id, s.subject_name, s.credit, " +
                       "g.grade, g.grade_point " +
                       "FROM enrollment e " +
                       "JOIN subject s ON e.sub_id = s.sub_id " +
                       "JOIN grade g ON e.grade = g.grade " +
                       "WHERE e.st_id = ? " +
                       "ORDER BY e.semester_no";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // 1. Create the inner objects from the JOINed data
                Subject subject = new Subject(
                    rs.getInt("sub_id"),
                    rs.getString("subject_name"),
                    rs.getInt("credit")
                );
                Grade grade = new Grade(
                    rs.getString("grade"),
                    rs.getFloat("grade_point")
                );

                // 2. Create the main Enrollment object
                Enrollment enrollment = new Enrollment(
                    rs.getInt("enrollment_id"),
                    rs.getInt("semester_no"),
                    subject,
                    grade
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
