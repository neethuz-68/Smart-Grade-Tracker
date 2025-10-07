package com.gradetracker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class DashboardView extends JFrame {

    private JButton gradeEntryButton;
    private JButton analysisButton;
    private JButton logoutButton;

    public DashboardView() {
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
                    g.setColor(new Color(230, 230, 250)); // Fallback Lavender color
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // ===== NAVBAR =====
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE); // Kept the white navbar for better visibility of the title
        navbar.setPreferredSize(new Dimension(0, 70));
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Smart Grade Tracker");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(Color.DARK_GRAY);
        navbar.add(title, BorderLayout.WEST);

        logoutButton = new JButton("Logout");
        // ... (styling for logoutButton)
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        logoutPanel.setBackground(Color.WHITE); // Match navbar color
        logoutPanel.add(logoutButton);
        navbar.add(logoutPanel, BorderLayout.EAST);

        // ===== CENTER CONTENT (Horizontal Cards) =====
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));

        gradeEntryButton = new CardButton("<html><center>Grade Entry +<br/>CGPA Calculation</center></html>");
        analysisButton = new CardButton("<html><center>GPA<br/>Analysis</center></html>");

        centerPanel.add(gradeEntryButton);
        centerPanel.add(analysisButton);

        // Add everything
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerPanel, BorderLayout.CENTER);
        add(background);
    }

    // ===== Methods for the Controller =====
    public void addGradeEntryListener(ActionListener listener) {
        gradeEntryButton.addActionListener(listener);
    }

    public void addAnalysisListener(ActionListener listener) {
        analysisButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public JButton getGradeEntryButton() {
        return gradeEntryButton;
    }

    public JButton getAnalysisButton() {
        return analysisButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    /**
     * A private inner class for the custom card-style buttons.
     * It's placed inside DashboardView because it's only used here.
     */
    private static class CardButton extends JButton {
        private final Color hoverBackgroundColor = new Color(245, 245, 245);
        private final Color pressedBackgroundColor = new Color(230, 230, 230);
        private final Color normalBackgroundColor = Color.WHITE;

        public CardButton(String text) {
            super(text);
            setBackground(normalBackgroundColor);
            super.setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.DARK_GRAY);
            setFont(new Font("SansSerif", Font.BOLD, 22));
            setPreferredSize(new Dimension(300, 200));

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(hoverBackgroundColor);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(normalBackgroundColor);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g2.setColor(hoverBackgroundColor);
            } else {
                g2.setColor(getBackground());
            }

            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            g2.dispose();

            super.paintComponent(g);
        }
    }
} 