package com.gradetracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.gradetracker.model.Enrollment;
import com.gradetracker.model.Student;
import com.gradetracker.dao.EnrollmentDAO;
import com.gradetracker.dao.EnrollmentDAOImpl;
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
    // Inside AuthController.java

    
private void handleLogin() {
String email = view.getUsername(); // This method gets the text from the "Username" field
        String password = view.getPassword();

    Student student = studentDAO.validateUser(email, password);

if (student != null) { // If validateUser was successful
    view.dispose();

    // Use the EnrollmentDAO to get all academic records for this student
    EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl(); // Or your class name
    List<Enrollment> enrollments = enrollmentDAO.findByStudentId(student.getStId());

    // Add these records to the student object
    for (Enrollment en : enrollments) {
        student.addEnrollment(en);
    }
    
    // Now you have a fully populated student object
    DashboardView dashboardView = new DashboardView();
    new DashboardController(dashboardView, student); // Pass the full object
    dashboardView.setVisible(true);
}

}
    
    /*private void handleLogin() {
        // --- BUG FIX --- Renamed 'username' to 'email' to match the database query
        
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
        }*/

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
        } 
    }*/
}