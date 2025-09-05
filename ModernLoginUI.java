import javax.swing.*;
import java.awt.*;

// Gradient background panel
class GradientPanel extends JPanel {
    private Color c1, c2;

    public GradientPanel(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class ModernLoginUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}

// ---------------- LOGIN PAGE ----------------
class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login - Grade Calculator");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel background = new GradientPanel(
            Color.decode("#EECDA3"),
            Color.decode("#EF629F")
        );
        background.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(800, 500));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // LEFT (Image placeholder)
        JLabel imageLabel = new JLabel("IMAGE", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 400));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));
        card.add(imageLabel, BorderLayout.WEST);

        // RIGHT (Form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Sign in");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(18);
        usernameField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(18);
        passwordField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Login button
        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.decode("#48C9B0"));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(250, 40));
        formPanel.add(loginButton, gbc);

        // Register link
        gbc.gridy++;
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setBackground(Color.WHITE);
        JLabel noAccount = new JLabel("Don't have an account? ");
        JButton registerButton = new JButton("Register");
        registerButton.setForeground(new Color(30, 144, 255));
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });
        bottomPanel.add(noAccount);
        bottomPanel.add(registerButton);
        formPanel.add(bottomPanel, gbc);

        card.add(formPanel, BorderLayout.CENTER);
        background.add(card);
        add(background);
        setVisible(true);
    }
}

// ---------------- REGISTER PAGE ----------------
class RegisterPage extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;

    public RegisterPage() {
        setTitle("Register - Grade Calculator");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel background = new GradientPanel(
            Color.decode("#EECDA3"),
            Color.decode("#EF629F")
        );
        background.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(800, 550));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // LEFT (Image placeholder)
        JLabel imageLabel = new JLabel("IMAGE", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 450));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));
        card.add(imageLabel, BorderLayout.WEST);

        // RIGHT (Form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Create New Account");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(18);
        usernameField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Email
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(18);
        emailField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Password
        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(18);
        passwordField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridy++;
        JLabel confirmLabel = new JLabel("Confirm:");
        gbc.gridx = 0;
        formPanel.add(confirmLabel, gbc);

        confirmPasswordField = new JPasswordField(18);
        confirmPasswordField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Register button
        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(Color.decode("#FF69B4"));
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBtn.setPreferredSize(new Dimension(250, 40));
        formPanel.add(registerBtn, gbc);

        // Login link
        gbc.gridy++;
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        loginPanel.setBackground(Color.WHITE);
        JLabel msg = new JLabel("Already have an account? ");
        JButton loginBtn = new JButton("Login");
        loginBtn.setForeground(new Color(30, 144, 255));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
        loginPanel.add(msg);
        loginPanel.add(loginBtn);
        formPanel.add(loginPanel, gbc);

        card.add(formPanel, BorderLayout.CENTER);
        background.add(card);
        add(background);
        setVisible(true);
    }
}
