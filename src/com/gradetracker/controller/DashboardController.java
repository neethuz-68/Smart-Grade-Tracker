package com.gradetracker.controller;

import com.gradetracker.dao.StudentDAO;
//import com.gradetracker.dao.StudentDAOImpl;
import com.gradetracker.model.Student;
import com.gradetracker.view.AnalysisView;
import com.gradetracker.view.DashboardView;
import com.gradetracker.view.GradeEntryView;
import com.gradetracker.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the main dashboard. Handles navigation to other screens
 * and the logout process.
 */
public class DashboardController implements ActionListener {

    private final DashboardView view;
    private final Student currentStudent;

    public DashboardController(DashboardView view, Student student) {
        this.view = view;
        this.currentStudent = student;

        // Register this controller to listen for clicks on all dashboard buttons
        this.view.addGradeEntryListener(this);
        this.view.addAnalysisListener(this);
        this.view.addLogoutListener(this);
    }

    /**
     * This method is called when any of the listened-to buttons are clicked.
     * It checks which button was pressed and calls the appropriate method.
     */
    // Inside DashboardController.java

@Override
public void actionPerformed(ActionEvent e) {
    // Get the actual object that was clicked
    Object source = e.getSource();

    // Compare the source object to the button objects from the view
    if (source == view.getGradeEntryButton()) {
        openGradeEntryScreen();
    } else if (source == view.getAnalysisButton()) {
        openAnalysisScreen();
    } else if (source == view.getLogoutButton()) {
        handleLogout();
    }
}
    /**
     * Creates and displays the Grade Entry screen.
     */
    private void openGradeEntryScreen() {
        GradeEntryView gradeEntryView = new GradeEntryView();
        new GradeEntryController(gradeEntryView, currentStudent);
        gradeEntryView.setVisible(true);
    }

    /**
     * Creates and displays the Performance Analysis screen.
     */
    private void openAnalysisScreen() {
        AnalysisView analysisView = new AnalysisView();
        new Analysis(analysisView, currentStudent);
        analysisView.setVisible(true);
    }

    /**
     * Handles the logout process by closing the dashboard and reopening the login screen.
     */
    private void handleLogout() {
        view.dispose(); // Close the current dashboard window

        // Restart the login sequence
        LoginView loginView = new LoginView();
        StudentDAO studentDAO = new StudentDAO();
        new AuthController(loginView, studentDAO);
        loginView.setVisible(true);
    }
}