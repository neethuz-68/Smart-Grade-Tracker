package com.gradetracker.controller;

import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.SemesterDAOImpl;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import com.gradetracker.view.AnalysisView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for the Analysis screen.
 * It fetches all academic data, orchestrates the SGPA/CGPA calculations,
 * and prepares the data for visual representation.
 */
public class Analysis {

    private final AnalysisView view;
    private final Student currentStudent;
    private final SemesterDAO semesterDAO;

    public Analysis(AnalysisView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        this.semesterDAO = new SemesterDAOImpl();

        // Immediately perform the analysis when the controller is created
        performAnalysis();
    }

    /**
     * The main logic method for the analysis feature.
     */
    private void performAnalysis() {
        // 1. Use the DAO to fetch all semester data for the logged-in student
        List<Semester> allSemesters = semesterDAO.getAllSemestersForStudent(currentStudent.getStudentId());
        
        // 2. Update the student object with their full academic history
        currentStudent.setSemesters(allSemesters);

        // 3. Prepare the data for the SGPA trend chart
        Map<String, Double> chartData = prepareChartData();

        // 4. Use the model to calculate the overall CGPA
        double overallCGPA = currentStudent.calculateCGPA();

        // 5. Tell the View to display the results
        view.displayChart(chartData);
        view.displayOverallCGPA(overallCGPA);
    }

    /**
     * Processes the list of semesters to create a map suitable for charting.
     * Each entry in the map is (Semester Name -> SGPA).
     */
    private Map<String, Double> prepareChartData() {
        Map<String, Double> data = new LinkedHashMap<>(); // Use LinkedHashMap to keep semesters in order
        for (Semester sem : currentStudent.getSemesters()) {
            data.put("Sem " + sem.getSemesterNumber(), sem.calculateSGPA());
        }
        return data;
    }
}