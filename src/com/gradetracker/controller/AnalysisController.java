package com.gradetracker.controller;

import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import java.util.Map;
import java.util.TreeMap;

/**
 * Controller for the Analysis screen.
 * It uses a pre-loaded Student object to perform calculations.
 */
public class AnalysisController {
    
    // The controller no longer needs a DAO.
    
    public AnalysisController() {}

    public Map<Integer, Double> getSgpaBySemester(Student student) {
        Map<Integer, Double> sgpaMap = new TreeMap<>();
        for (Semester sem : student.getAllSemesters()) {
            sgpaMap.put(sem.getSemesterNo(), sem.calculateSgpa());
        }
        return sgpaMap;
    }
    
    public double getOverallCgpa(Student student) {
        return student.calculateCgpa();
    }
}
/*package com.gradetracker.controller;

import com.gradetracker.model.Student;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Enrollment;
import com.gradetracker.dao.EnrollmentDAO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalysisController {

    private final EnrollmentDAO enrollmentDAO;

    public AnalysisController(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    public Map<Integer, Double> getSgpaBySemester(Student student) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(student.getStId());

        for (Enrollment e : enrollments) {
            student.addEnrollment(e);
        }

        Map<Integer, Double> sgpaMap = new TreeMap<>();
        for (Semester sem : student.getAllSemesters()) {
            sgpaMap.put(sem.getSemesterNo(), sem.calculateSgpa());
        }

        return sgpaMap;
    }
}*/
