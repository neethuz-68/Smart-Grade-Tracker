package com.gradetracker.dao;

import com.gradetracker.model.Enrollment;
import java.util.List;

public interface EnrollmentDAO {
    boolean createEnrollment(Enrollment enrollment);
    List<Enrollment> findByStudentId(int studentId);
}