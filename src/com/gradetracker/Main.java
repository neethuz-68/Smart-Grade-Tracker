package com.gradetracker;

import com.gradetracker.controller.AuthController;
import com.gradetracker.db.DatabaseManager;
import com.gradetracker.view.LoginView;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main method started.");
        
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("1. Creating DatabaseManager...");
                DatabaseManager dbManager = new DatabaseManager();

                System.out.println("2. Connecting to database...");
                boolean isConnected = dbManager.connect();

                if (isConnected) {
                    System.out.println("   Database connection successful.");
                    
                    System.out.println("3. Creating LoginView...");
                    LoginView loginView = new LoginView();

                    System.out.println("4. Creating AuthController...");
                    new AuthController(loginView, dbManager);

                    System.out.println("5. Making LoginView visible...");
                    loginView.setVisible(true);
                    
                    System.out.println("   Startup sequence complete. Window should be visible.");

                } else {
                    String errorMessage = "CRITICAL: Could not connect to the database. Application will exit.";
                    System.err.println(errorMessage);
                    JOptionPane.showMessageDialog(null, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                // This will catch ANY unexpected error during startup
                String errorMessage = "An unexpected error occurred during startup:\n" + e.getMessage();
                System.err.println(errorMessage);
                e.printStackTrace(); // Prints detailed error to console

                // This pop-up ensures you see the error even if the console closes
                JOptionPane.showMessageDialog(null, errorMessage, "Application Startup Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}