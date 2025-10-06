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
        navbar.setOpaque(false); // Make navbar transparent
        navbar.setPreferredSize(new Dimension(0, 80));
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Smart Grade Tracker");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.DARK_GRAY);
        navbar.add(title, BorderLayout.WEST);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        // ... (styling for logoutButton)
        navbar.add(logoutButton, BorderLayout.EAST);

        // ===== CENTER CONTENT (Horizontal Cards) =====
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0)); // Horizontal layout with a gap of 80px
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0)); // Padding from top

        // Create the new custom card-style buttons
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
}

/**
 * A custom JButton class that is drawn as a rounded rectangle to look like a card.
 */
class CardButton extends JButton {
    private Color hoverBackgroundColor = new Color(245, 245, 245);
    private Color pressedBackgroundColor = Color.WHITE;

    public CardButton(String text) {
        super(text);
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
                setBackground(Color.WHITE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Determine color based on button state (pressed, hover, normal)
        if (getModel().isPressed()) {
            g2.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverBackgroundColor);
        } else {
            g2.setColor(getBackground());
        }
        
        // Draw the rounded rectangle shape
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        g2.dispose();

        super.paintComponent(g);
    }
}