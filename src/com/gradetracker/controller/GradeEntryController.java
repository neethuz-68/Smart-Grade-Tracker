package com.gradetracker.controller;

import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.dao.SemesterDAOImpl;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import com.gradetracker.model.Subject;
import com.gradetracker.view.LoginView;
import com.gradetracker.view.GradeEntryView;
import com.gradetracker.view.DashboardView; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GradeEntryController {
    private GradeEntryView view;
    private Student currentStudent;
    private SemesterDAO semesterDAO;

    public GradeEntryController(GradeEntryView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        this.semesterDAO = new SemesterDAOImpl();
        
        this.view.addSaveListener(new SaveListener());
        this.view.addDashboardListener(new DashboardListener());
        this.view.addViewAnalysisListener(new ViewAnalysisListener());
        this.view.addLogoutListener(new LogoutListener());
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
            view.displayMessage("View Analysis feature - implement as needed");
        }
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogout();
        }
    }

    private void handleSaveAndCalculate() {
        int semesterNo = view.getSemesterNumber();
        List<Subject> subjects = view.getSubjectsData();
        
        if (semesterNo <= 0 || subjects.isEmpty()) {
            view.displayMessage("Please enter a valid semester number and at least one subject.");
            return;
        }
        
        Semester newSemester = new Semester(0, semesterNo);
        newSemester.setSubjects(subjects);
        
        boolean success = semesterDAO.saveSemester(currentStudent.getStudentId(), newSemester);
        
        if (!success) {
            view.displayMessage("Error: Could not save grades to the database.");
            return;
        }
        
        currentStudent.addSemester(newSemester);
        double sgpa = newSemester.calculateSGPA();
        double cgpa = currentStudent.calculateCGPA();
        
        view.displayResults(sgpa, cgpa);
        view.displayMessage("Grades saved successfully!");
    }

    private void handleDashboardNavigation() {
        //view.dispose();
        DashboardView dashboardView = new DashboardView();
        new DashboardController(dashboardView, currentStudent);
        dashboardView.setVisible(true);
        
    }

    private void handleLogout() {
        view.dispose();
        LoginView loginview = new LoginView();
        StudentDAO studentDAO = new StudentDAO();
        new AuthController(loginview, studentDAO);
        loginview.setVisible(true);
        //view.displayMessage("Logged out successfully!");
    }
}