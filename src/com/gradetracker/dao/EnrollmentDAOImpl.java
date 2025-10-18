//package com.gradetracker.dao;

//public class EnrollmentDAOImpl {
    
//}
package com.gradecalculator.dao.impl;

import com.gradecalculator.dao.EnrollmentDAO;
import com.gradecalculator.dao.DAOException;
import com.gradecalculator.models.Enrollment;
import com.gradecalculator.models.Subject;
import com.gradecalculator.models.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    private final Connection connection;

    public EnrollmentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Enrollment enrollment) throws DAOException {
        String sql = "INSERT INTO enrollments (enrollment_id, subject_id, grade_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, enrollment.getEnrollmentId());
            ps.setInt(2, enrollment.getSubject().getSubjectId());  // assume Subject has getSubjectId()
            ps.setInt(3, enrollment.getGrade().getGradeId());      // assume Grade has getGradeId()
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error saving Enrollment", e);
        }
    }

    @Override
    public Optional<Enrollment> findById(int enrollmentId) throws DAOException {
        String sql = "SELECT enrollment_id, subject_id, grade_id FROM enrollments WHERE enrollment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int subjId = rs.getInt("subject_id");
                    int gradeId = rs.getInt("grade_id");
                    Subject subject = loadSubject(subjId);
                    Grade grade = loadGrade(gradeId);
                    Enrollment enrollment = new Enrollment(enrollmentId, /*semNo*/ 0, subject, grade);
                    return Optional.of(enrollment);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error finding Enrollment by id", e);
        }
    }

    @Override
    public List<Enrollment> findAll() throws DAOException {
        String sql = "SELECT enrollment_id, subject_id, grade_id FROM enrollments";
        List<Enrollment> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("enrollment_id");
                int subjId = rs.getInt("subject_id");
                int gradeId = rs.getInt("grade_id");
                Subject subject = loadSubject(subjId);
                Grade grade = loadGrade(gradeId);
                Enrollment enrollment = new Enrollment(id, /*semNo*/ 0, subject, grade);
                list.add(enrollment);
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Error finding all Enrollments", e);
        }
    }

    @Override
    public void update(Enrollment enrollment) throws DAOException {
        String sql = "UPDATE enrollments SET subject_id = ?, grade_id = ? WHERE enrollment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, enrollment.getSubject().getSubjectId());
            ps.setInt(2, enrollment.getGrade().getGradeId());
            ps.setInt(3, enrollment.getEnrollmentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating Enrollment", e);
        }
    }

    @Override
    public void deleteById(int enrollmentId) throws DAOException {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting Enrollment by id", e);
        }
    }

    @Override
    public List<Enrollment> findBySubjectId(int subjectId) throws DAOException {
        String sql = "SELECT enrollment_id, subject_id, grade_id FROM enrollments WHERE subject_id = ?";
        List<Enrollment> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("enrollment_id");
                    int gradeId = rs.getInt("grade_id");
                    Subject subject = loadSubject(subjectId);
                    Grade grade = loadGrade(gradeId);
                    Enrollment enrollment = new Enrollment(id, /*semNo*/ 0, subject, grade);
                    list.add(enrollment);
                }
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Error finding Enrollments by subjectId", e);
        }
    }

    @Override
    public List<Enrollment> findByGradeId(int gradeId) throws DAOException {
        String sql = "SELECT enrollment_id, subject_id, grade_id FROM enrollments WHERE grade_id = ?";
        List<Enrollment> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, gradeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("enrollment_id");
                    int subjId = rs.getInt("subject_id");
                    Subject subject = loadSubject(subjId);
                    Grade grade = loadGrade(gradeId);
                    Enrollment enrollment = new Enrollment(id, /*semNo*/ 0, subject, grade);
                    list.add(enrollment);
                }
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Error finding Enrollments by gradeId", e);
        }
    }

    // Helper methods to load Subject & Grade by ID — you must implement or inject SubjectDAO / GradeDAO
    private Subject loadSubject(int subjectId) throws DAOException {
        // Example stub; implement properly
        throw new UnsupportedOperationException("loadSubject not implemented yet");
    }

    private Grade loadGrade(int gradeId) throws DAOException {
        // Example stub; implement properly
        throw new UnsupportedOperationException("loadGrade not implemented yet");
    }
}

