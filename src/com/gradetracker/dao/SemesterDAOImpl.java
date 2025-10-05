package com.gradetracker.dao;

import com.gradetracker.db.DatabaseManager;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAOImpl implements SemesterDAO {

    @Override
    public List<Semester> getAllSemestersForStudent(int studentId) {
        List<Semester> semesters = new ArrayList<>();
        // ... (Full implementation from previous answers to fetch semesters and their subjects/grades)
        return semesters;
    }

    @Override
    public boolean saveSemester(int studentId, Semester semester) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Insert Semester and get its generated ID
            String semesterSql = "INSERT INTO Semester (student_id, semester_no) VALUES (?, ?)";
            PreparedStatement semesterPstmt = conn.prepareStatement(semesterSql, Statement.RETURN_GENERATED_KEYS);
            semesterPstmt.setInt(1, studentId);
            semesterPstmt.setInt(2, semester.getSemesterNumber());
            semesterPstmt.executeUpdate();
            ResultSet semesterKeys = semesterPstmt.getGeneratedKeys();
            if (!semesterKeys.next()) throw new SQLException("Creating semester failed, no ID obtained.");
            int semesterId = semesterKeys.getInt(1);

            // 2. Insert Subjects and Grades
            String subjectSql = "INSERT INTO Subject (semester_id, subject_name, credit) VALUES (?, ?, ?)";
            String gradeSql = "INSERT INTO Grade (subject_id, grade_letter, grade_point) VALUES (?, ?, ?)";

            for (Subject subject : semester.getSubjects()) {
                // Insert Subject
                PreparedStatement subjectPstmt = conn.prepareStatement(subjectSql, Statement.RETURN_GENERATED_KEYS);
                subjectPstmt.setInt(1, semesterId);
                subjectPstmt.setString(2, subject.getSubjectName());
                subjectPstmt.setFloat(3, subject.getCredits());
                subjectPstmt.executeUpdate();
                ResultSet subjectKeys = subjectPstmt.getGeneratedKeys();
                if (!subjectKeys.next()) throw new SQLException("Creating subject failed, no ID obtained.");
                int subjectId = subjectKeys.getInt(1);

                // Insert Grade
                PreparedStatement gradePstmt = conn.prepareStatement(gradeSql);
                gradePstmt.setInt(1, subjectId);
                gradePstmt.setString(2, subject.getGrade());
                gradePstmt.setFloat(3, subject.getGradePoint());
                gradePstmt.executeUpdate();
            }

            conn.commit(); // Finalize the transaction
            return true;
        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}