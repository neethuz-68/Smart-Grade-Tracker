package com.gradetracker.controller;

import com.gradetracker.model.Student;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Enrollment;
import com.gradetracker.dao.EnrollmentDAO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Controller class responsible for fetching and processing
 * SGPA data for the AnalysisView.
 */
public class AnalysisController {

    private final EnrollmentDAO enrollmentDAO;

    public AnalysisController(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    /**
     * Loads all enrollments for a student, groups them by semester,
     * and calculates SGPA for each semester.
     * 
     * @param student Logged-in student
     * @return Map of semester number -> SGPA
     */
    public Map<Integer, Double> getSgpaBySemester(Student student) {
        // Step 1: Fetch all enrollments of the student
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(student.getStId());

        // Step 2: Add them to student's semester map
        for (Enrollment e : enrollments) {
            student.addEnrollment(e);
        }

        // Step 3: Calculate SGPA for each semester
        Map<Integer, Double> sgpaMap = new TreeMap<>();
        for (Semester sem : student.getAllSemesters()) {
            sgpaMap.put(sem.getSemesterNo(), sem.calculateSgpa());
        }

        return sgpaMap;
    }
}
