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
        // --- BUG FIX --- Renamed 'username' to 'email' to match the database query
        String email = view.getUsername(); // This method gets the text from the "Username" field
        String password = view.getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            view.displayMessage("Username and password cannot be empty.");
            return;
        }

        // Pass the 'email' variable to the DAO method
        Student student = studentDAO.validateUser(email, password);

        if (student != null) {
            //view.displayMessage("Login Successful!");
            view.dispose();
            DashboardView dashboard = new DashboardView();
            dashboard.setVisible(true);
        } else {
            view.displayMessage("Invalid username or password.");
        }

        /*if (student != null) {
            view.displayMessage("Login Successful!");
            view.dispose(); 
            StudentDAO studentDAO = new StudentDAO(); // Assuming you have access or create it
            Student fullStudentData = studentDAO.getStudentData(student); // You will need to implement this method

    // 2. Create the DashboardView
            DashboardView dashboardView = new DashboardView();
    
    // 3. Create the DashboardController to manage it
            new DashboardController(dashboardView, fullStudentData);
    
    // 4. Make the dashboard visible
            dashboardView.setVisible(true);

        } else {
    // If login fails...
            view.displayMessage("Invalid username or password.");
        } */
    }
}