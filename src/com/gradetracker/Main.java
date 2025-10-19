package com.gradetracker;

import com.gradetracker.controller.AuthController;
import com.gradetracker.dao.StudentDAO;
import com.gradetracker.dao.StudentDAOImpl;
import com.gradetracker.view.LoginView;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentDAOImpl studentDAOImpl = new StudentDAOImpl();

            LoginView loginView = new LoginView();

            new AuthController(loginView, studentDAOImpl);

            loginView.setVisible(true);
        });
    }
}