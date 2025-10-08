package com.gradetracker.controller;

import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.SemesterDAOImpl;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import com.gradetracker.model.Subject;
import com.gradetracker.view.GradeEntryView;
import com.gradetracker.view.DashboardView; // Import your Dashboard view
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
        
        // Add listeners
        this.view.addSaveListener(new SaveListener());
        this.view.addDashboardListener(new DashboardListener());
        this.view.addViewAnalysisListener(new ViewAnalysisListener());
        this.view.addLogoutListener(new LogoutListener());
    }

    // Inner class for Save button
    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleSaveAndCalculate();
        }
    }

    // Inner class for Dashboard button
    private class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleDashboardNavigation();
        }
    }

    // Inner class for View Analysis button
    private class ViewAnalysisListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your view analysis logic here
            view.displayMessage("View Analysis feature - implement as needed");
        }
    }

    // Inner class for Logout button
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
        // Close the current Grade Entry view
        view.dispose();
        
        // Open the Dashboard view
        DashboardView dashboardView = new DashboardView(currentStudent);
        dashboardView.setVisible(true);
        
        // If you have a DashboardController, initialize it here:
        // DashboardController dashboardController = new DashboardController(dashboardView, currentStudent);
    }

    private void handleLogout() {
        // Close current view
        view.dispose();
        
        // Open login view or perform logout logic
        // For example:
        // LoginView loginView = new LoginView();
        // loginView.setVisible(true);
        // new LoginController(loginView);
        
        view.displayMessage("Logged out successfully!");
    }
}











// package com.gradetracker.controller;

// import com.gradetracker.dao.SemesterDAO;
// import com.gradetracker.dao.SemesterDAOImpl;
// import com.gradetracker.model.Semester;
// import com.gradetracker.model.Student;
// import com.gradetracker.model.Subject;
// import com.gradetracker.view.GradeEntryView;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// public class GradeEntryController implements ActionListener {

//     private GradeEntryView view;
//     private Student currentStudent;
//     private SemesterDAO semesterDAO;

//     public GradeEntryController(GradeEntryView view, Student student) {
//         this.view = view;
//         this.currentStudent = student;
//         this.semesterDAO = new SemesterDAOImpl();
//         this.view.addSaveListener(this);
//     } 

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         handleSaveAndCalculate();
//     } 

//     private void handleSaveAndCalculate() {
//         int semesterNo = view.getSemesterNumber();
//         List<Subject> subjects = view.getSubjectsData();

//         if (semesterNo <= 0 || subjects.isEmpty()) {
//             view.displayMessage("Please enter a valid semester number and at least one subject.");
//             return;
//         }

//         Semester newSemester = new Semester(0, semesterNo);
//         newSemester.setSubjects(subjects);

//         boolean success = semesterDAO.saveSemester(currentStudent.getStudentId(), newSemester);
//         if (!success) {
//             view.displayMessage("Error: Could not save grades to the database.");
//             return;
//         }

//         currentStudent.addSemester(newSemester);

//         double sgpa = newSemester.calculateSGPA();
//         double cgpa = currentStudent.calculateCGPA();

//         view.displayResults(sgpa, cgpa);
//         view.displayMessage("Grades saved successfully!");

//     }

// } 
