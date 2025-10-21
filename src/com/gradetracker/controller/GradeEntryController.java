package com.gradetracker.controller;

import com.gradetracker.dao.*;
import com.gradetracker.model.*;
import com.gradetracker.view.AnalysisView;
import com.gradetracker.view.DashboardView;
import com.gradetracker.view.GradeEntryView;
import com.gradetracker.view.LoginView;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class GradeEntryController { 

    private final GradeEntryView view;
    private final Student currentStudent;
    private final EnrollmentDAO enrollmentDAO;
    private final SubjectDAO subjectDAO;
    private final GradeDAO gradeDAO;

    public GradeEntryController(GradeEntryView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        
        this.enrollmentDAO = new EnrollmentDAOImpl();
        this.subjectDAO = new SubjectDAOImpl();
        this.gradeDAO = new GradeDAOImpl();
        
        this.view.addSaveListener(new SaveListener());
        this.view.addDashboardListener(new DashboardListener());
        this.view.addViewAnalysisListener(new ViewAnalysisListener()); 
        this.view.addLogoutListener(new LogoutListener());

        populateSubjectsDropdown();
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleSaveAndCalculate();
        }
    }

    private class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleDashboardNavigation();
        }
    }
    private class ViewAnalysisListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAnalysisScreen();
        }
    }
    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogout();
        }
    }
    private void populateSubjectsDropdown() {
        List<Subject> allSubjects = subjectDAO.getAllSubjects();
        view.populateSubjectDropdown(allSubjects);
    }

    private void handleSaveAndCalculate() {
        int semesterNo = view.getSemesterNumber();
        GradeEntryView.SubjectData data = view.getSingleSubjectData(); 

        if (semesterNo <= 0 || data == null) {
            view.displayMessage("Please select a semester, subject, and grade.");
            return;
        }
        Subject subject = data.subject();


        Map<String, Grade> gradeMap = gradeDAO.getAllGrades();
    Grade grade = gradeMap.get(data.gradeLetter().toUpperCase());

    if (grade == null) {
        view.displayMessage("Invalid grade letter entered.");
        return;
    }


        Enrollment enrollment = new Enrollment(currentStudent.getStId(),currentStudent.getStId(),semesterNo,subject,grade);

        if (enrollmentDAO.createEnrollment(enrollment)) {
            currentStudent.addEnrollment(enrollment);

            double sgpa = currentStudent.getSgpa(semesterNo);
            double cgpa = currentStudent.calculateCgpa();

            view.displayResults(sgpa, cgpa);
            view.displayMessage("Subject saved successfully!");
        } else {
            view.displayMessage("Error: Could not save subject.");
        }
    }

    private void handleDashboardNavigation() {
        view.dispose(); 
        DashboardView dashboardView = new DashboardView();
        new DashboardController(dashboardView, currentStudent);
        dashboardView.setVisible(true);
    }
    
    private void openAnalysisScreen() {
    AnalysisView analysisView = new AnalysisView();
    analysisView.setTitle("Performance Analysis - " + currentStudent.getName());

    AnalysisController analysisController = new AnalysisController();

    Map<Integer, Double> sgpaData = analysisController.getSgpaBySemester(currentStudent);
    double overallCGPA = analysisController.getOverallCgpa(currentStudent);

    analysisView.displayChart(sgpaData);
    analysisView.displayOverallCGPA(overallCGPA);

    analysisView.setVisible(true);
}

    private void handleLogout() {
        view.dispose();
        LoginView loginView = new LoginView();
        StudentDAO studentDAO = new StudentDAOImpl(); 
        new AuthController(loginView, studentDAO);
        loginView.setVisible(true);
    }
}