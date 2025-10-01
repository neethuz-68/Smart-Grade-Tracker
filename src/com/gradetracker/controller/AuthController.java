package com.gradetracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gradetracker.model.Student;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.view.LoginView;
import com.gradetracker.view.DashboardView;

public class AuthController implements ActionListener {
    private LoginView view;
     private final StudentDAO studentDAO;

    public AuthController(LoginView view, StudentDAO studentDAO) {
        this.view = view;
        this.studentDAO = studentDAO;
        this.view.addLoginListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        handleLogin();
    }
    private void handleLogin() {
        // 1. Get user input from the view
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            view.displayMessage("Username and password cannot be empty.");
            return;
        }

        Student student = studentDAO.validateUser(username, password);

        if (student != null) {
            // If the returned student object is not null, login is successful.
            view.displayMessage("Login Successful!");
            view.dispose(); // Close the login window.

            // Launch the main dashboard.
            DashboardView dashboard = new DashboardView();
            // TODO: In a full application, you would create and link a DashboardController here.
            dashboard.setVisible(true);

        } else {
            // If the returned student object is null, the credentials were invalid.
            view.displayMessage("Invalid username or password.");
        }
    }

}