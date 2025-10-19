package com.gradetracker.controller;

import com.gradetracker.dao.EnrollmentDAO;
import com.gradetracker.dao.EnrollmentDAOImpl;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.model.Enrollment;
import com.gradetracker.model.Student;
import com.gradetracker.view.DashboardView;
import com.gradetracker.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AuthController implements ActionListener {
    private final LoginView view;
    private final StudentDAO studentDAO;

    public AuthController(LoginView view, StudentDAO studentDAO) {
        this.view = view;
        this.studentDAO = studentDAO;
        this.view.addLoginListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        handleLogin();
    }
    
    private void handleLogin() {
        String email = view.getUsername();
        String password = view.getPassword();

        // Basic validation
        if (email.isEmpty() || password.isEmpty()) {
            view.displayMessage("Username and password cannot be empty.");
            return;
        }

        Student student = studentDAO.validateUser(email, password);

        if (student != null) {
            try {
                view.dispose(); 

                EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
                List<Enrollment> enrollments = enrollmentDAO.findByStudentId(student.getStId());

                for (Enrollment en : enrollments) {
                    student.addEnrollment(en);
                }
                
                DashboardView dashboardView = new DashboardView();
                new DashboardController(dashboardView, student);
                dashboardView.setVisible(true);

            } catch (Exception e) {
                view.displayMessage("An error occurred after login: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            view.displayMessage("Invalid username or password.");
        }
    }
}