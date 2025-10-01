package ui;

import models.Student;
import db.StudentDAO;

import javax.swing.*;
import java.awt.*;

class ModernLoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public void LoginPage() {
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageBackgroundPanel background = new ImageBackgroundPanel("bg1.jpg");
        background.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 230)); // semi-transparent white
        card.setPreferredSize(new Dimension(1000, 600));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel imageLabel = new JLabel("IMAGE", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 500));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));
        card.add(imageLabel, BorderLayout.WEST);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 15, 10);

        JLabel title = new JLabel("Sign in to Grade Calculator");
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(title, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(new JLabel("Username"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(250, 40));
        formPanel.add(usernameField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(new JLabel("Password"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 40));
        formPanel.add(passwordField, gbc);

        // LOGIN BUTTON
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.decode("#48C9B0"));
        loginButton.setPreferredSize(new Dimension(250, 40));
        formPanel.add(loginButton, gbc);

        // login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            StudentDAO dao = new StudentDAO();
            Student student = dao.authenticate(username, password);

            if (student != null) {
                JOptionPane.showMessageDialog(this, "Welcome " + student.getName() + "!");
                dispose();
                new DashboardPage(student); // pass logged-in student
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });

        // REGISTER LINK
        gbc.gridy++;
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setBackground(new Color(255, 255, 255, 0));
        bottomPanel.add(new JLabel("Don't have an account? "));
        JButton registerButton = new JButton("Register");
        registerButton.setForeground(new Color(30, 144, 255));
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> { dispose(); new RegisterPage(); });
        bottomPanel.add(registerButton);
        formPanel.add(bottomPanel, gbc);

        card.add(formPanel, BorderLayout.CENTER);
        background.add(card);
        add(background);
        setVisible(true);
    }
}
