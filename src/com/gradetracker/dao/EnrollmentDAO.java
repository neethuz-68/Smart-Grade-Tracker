package com.gradecalculator.dao;

import com.gradecalculator.models.Enrollment;
import java.util.List;

public interface EnrollmentDAO {
    boolean createEnrollment(Enrollment enrollment);
    List<Enrollment> findByStudentId(int studentId);
}