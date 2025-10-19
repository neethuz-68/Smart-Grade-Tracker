package com.gradetracker;

import com.gradetracker.controller.AuthController;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.dao.StudentDAOImpl;
import com.gradetracker.view.LoginView;

import javax.swing.SwingUtilities;

/**
 * The main entry point for the Smart Grade Tracker application.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Create the Data Access Object for student operations
            StudentDAOImpl studentDAOImpl = new StudentDAOImpl();

            // 2. Create the initial view (the login screen)
            LoginView loginView = new LoginView();

            // 3. Create the controller and wire the view and DAO together
            new AuthController(loginView, studentDAOImpl);

            // 4. Launch the application by making the login screen visible
            loginView.setVisible(true);
        });
    }
}