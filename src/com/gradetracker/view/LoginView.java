package com.gradetracker.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        // --- Frame Setup ---
        setTitle("Smart Grade Tracker - Login");
        setSize(480, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Gradient Background Panel ---
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250); // light sky blue
                Color color2 = new Color(123, 104, 238); // medium slate blue
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // --- Card Panel with Shadow ---
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Shadow settings
                g2.setColor(new Color(0, 0, 0, 60)); // semi-transparent black
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20); // shadow
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panel.setPreferredSize(new Dimension(360, 240));
        panel.setBackground(new Color(255, 255, 255, 230)); // semi-transparent white
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // --- Title Label ---
        JLabel titleLabel = new JLabel("Smart Grade Tracker");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(72, 61, 139)); // dark slate blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // --- Username Label & Field with Icon ---
        JLabel userLabel = new JLabel("\uD83D\uDC64"); // user icon
        userLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(18);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(100, 149, 237), 2, true), // cornflower blue border
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        usernameField.setForeground(new Color(25, 25, 112));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gbc);

        // --- Password Label & Field with Icon ---
        JLabel passLabel = new JLabel("\uD83D\uDD12"); // lock icon
        passLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(18);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(100, 149, 237), 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        passwordField.setForeground(new Color(25, 25, 112));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, gbc);

        // --- Login Button ---
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(72, 61, 139)); // dark slate blue
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        // Hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(123, 104, 238)); // medium slate blue
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(72, 61, 139));
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        backgroundPanel.add(panel);
        this.add(backgroundPanel);
        getRootPane().setDefaultButton(loginButton);
    }

    // --- Public methods ---
    public String getUsername() { return usernameField.getText(); }
    public char[] getPassword() { return passwordField.getPassword(); }
    public void displayMessage(String message) { JOptionPane.showMessageDialog(this, message); }
    public void clearFields() { usernameField.setText(""); passwordField.setText(""); }
    public void addLoginListener(ActionListener listener) { loginButton.addActionListener(listener); }
}
