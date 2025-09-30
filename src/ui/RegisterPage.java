package ui;

import db.StudentDAO;

import javax.swing.*;
import java.awt.*;

class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterPage() {
        setTitle("Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageBackgroundPanel background = new ImageBackgroundPanel("bg1.jpg");
        background.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 230));
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

        JLabel title = new JLabel("Create New Account");
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

        // REGISTER BUTTON
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(Color.decode("#FF69B4"));
        registerBtn.setPreferredSize(new Dimension(250, 40));
        formPanel.add(registerBtn, gbc);

        // register button action
        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            StudentDAO dao = new StudentDAO();
            if (dao.register(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new LoginPage(); // back to login
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Try another username.");
            }
        });

        // LOGIN LINK
        gbc.gridy++;
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        loginPanel.setBackground(new Color(255, 255, 255, 0));
        loginPanel.add(new JLabel("Already have an account? "));
        JButton loginBtn = new JButton("Login");
        loginBtn.setForeground(new Color(30, 144, 255));
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> { dispose(); new LoginPage(); });
        loginPanel.add(loginBtn);
        formPanel.add(loginPanel, gbc);

        card.add(formPanel, BorderLayout.CENTER);
        background.add(card);
        add(background);
        setVisible(true);
    }
}
