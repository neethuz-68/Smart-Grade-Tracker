package com.gradetracker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class dashboard_2 extends JFrame {

    private JButton gradeEntryButton;
    private JButton analysisButton;
    //private JButton targetButton;
    private JButton logoutButton;

    public dashboard_2() {
        setTitle("Smart Grade Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Robust Image Loading ---
        URL imageUrl = getClass().getResource("/resources/bg1.jpg");
        ImageIcon bgIcon = (imageUrl != null) ? new ImageIcon(imageUrl) : null;
        Image bgImage = (bgIcon != null) ? bgIcon.getImage() : null;

        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.LIGHT_GRAY); // Fallback color
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
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

        logoutButton = new JButton("Logout");
        // ... (styling for logoutButton)
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutButton);
        navbar.add(logoutPanel, BorderLayout.EAST);

        // ===== CENTER CONTENT (Vertical Cards) =====
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 30));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 100, 400));

        gradeEntryButton = createNavButton("Grade Entry");
        analysisButton = createNavButton("CGPA Analysis");
        //targetButton = createNavButton("Target CGPA");

        centerPanel.add(createNavCard(gradeEntryButton));
        centerPanel.add(createNavCard(analysisButton));
        //centerPanel.add(createNavCard(targetButton));

        // Add everything
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerPanel, BorderLayout.CENTER);
        add(background);
    }

    // ===== Helper Methods =====
    private JPanel createNavCard(JButton button) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        // ... (styling for card)
        card.add(button);
        return card;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        // ... (styling for button)
        return btn;
    }

    // ===== Methods for the Controller =====
    public void addGradeEntryListener(ActionListener listener) {
        gradeEntryButton.addActionListener(listener);
    }

    public void addAnalysisListener(ActionListener listener) {
        analysisButton.addActionListener(listener);
    }



    /*public void addTargetListener(ActionListener listener) {
        targetButton.addActionListener(listener);
    }*/

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}