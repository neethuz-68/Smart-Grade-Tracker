package ui;
import models.Student;

import javax.swing.*;
import java.awt.*;

public class DashboardPage extends JFrame {

    public DashboardPage() {
        setTitle("Smart Grade Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Background Panel with Image =====
        ImageIcon bgIcon = new ImageIcon("bg1.jpg"); // Replace with your background image
        Image bgImage = bgIcon.getImage();

        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // ===== NAVBAR =====
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(0, 70));

        JLabel title = new JLabel("Smart Grade Tracker");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(Color.DARK_GRAY);
        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navbar.add(title, BorderLayout.WEST);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(41, 128, 185));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.setPreferredSize(new Dimension(120, 40));
        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logging out...");
            dispose();
        });

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutBtn);
        navbar.add(logoutPanel, BorderLayout.EAST);

        // ===== CENTER CONTENT (Vertical Cards) =====
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 30));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 100, 400));

        centerPanel.add(createNavCard("Grade Entry"));
        centerPanel.add(createNavCard("CGPA Analysis"));
        centerPanel.add(createNavCard("Target CGPA"));

        // Add everything
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerPanel, BorderLayout.CENTER);

        add(background);
        setVisible(true);
    }

    // ===== Helper: Create a White Card with White Button =====
    private JPanel createNavCard(String text) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(300, 120));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.DARK_GRAY); // Text dark gray
        btn.setBackground(Color.WHITE);     // Button white
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 60));
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        btn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opening " + text + "...");
        });

        card.add(btn);
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardPage::new);
    }
}

