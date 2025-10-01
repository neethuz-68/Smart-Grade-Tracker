package com.gradetracker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        // --- 1. Frame Setup ---
        setTitle("Smart Grade Tracker - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // --- 2. Create a Panel and Set a Layout ---
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use a flexible layout manager
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components

        // --- 3. Initialize and Add Components to the Panel ---
        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        // Username Text Field
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        // Password Field
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Align button to the right
        panel.add(loginButton, gbc);

        // --- 4. Add the Panel to the Frame ---
        this.add(panel);
    }
    
    // --- Public methods for the controller to use ---

    public String getUsername() {
        return usernameField.getText();
    }
    
    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}