package com.gradetracker.controller;

import com.gradetracker.dao.EnrollmentDAO;
import com.gradetracker.dao.EnrollmentDAOImpl;
import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.dao.SemesterDAOImpl;
import com.gradetracker.model.Enrollment;
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
    List<Subject> subjectsFromView = view.getSubjectsData(); // This might need modification
    
    EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
    boolean allSuccess = true;

    // Loop through each subject from the form
    for (Subject subject : subjectsFromView) {
        // Create an Enrollment object for this subject
        Enrollment enrollment = new Enrollment(
            0, // enrollmentId is auto-generated
            currentStudent.getStId(),
            subject.getSubId(), // You'll need a way to get the subject ID
            semesterNo,
            subject.getGrade() // You'll need the Grade object or letter
        );
        
        // Save this single enrollment record to the database
        if (!enrollmentDAO.createEnrollment(enrollment)) {
            allSuccess = false;
            break; // Stop if one fails
        }
    }

    if (allSuccess) {
        // ... refresh data and recalculate SGPA/CGPA ...
    }
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