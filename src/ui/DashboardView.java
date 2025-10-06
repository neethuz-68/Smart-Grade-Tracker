package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {

    private JButton gradeEntryButton;
    private JButton analysisButton;
    private JButton plannerButton;
    private JButton logoutButton; 

    public DashboardView() {
        // --- Frame Setup ---
        setTitle("Dashboard - Smart Grade Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //setLocationRelativeTo(null); // Center the window on the screen

        // --- UI Components ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 4 rows, 1 col, with spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Initialize buttons
        gradeEntryButton = new JButton("Enter / Edit Grades");
        analysisButton = new JButton("View Performance Analysis");
        //plannerButton = new JButton("Plan Target CGPA");
        logoutButton = new JButton("Logout");

        // Add buttons to the panel
        mainPanel.add(gradeEntryButton);
        mainPanel.add(analysisButton);
        //mainPanel.add(plannerButton);
        mainPanel.add(logoutButton);

        // Add the main panel to the frame
        this.add(mainPanel);
    }
    

    // --- Methods for Controllers to Add Listeners ---

    public void addGradeEntryListener(ActionListener listener) {
        gradeEntryButton.addActionListener(listener);
    }

    public void addAnalysisListener(ActionListener listener) {
        analysisButton.addActionListener(listener);
    }

    /*public void addPlannerListener(ActionListener listener) {
        plannerButton.addActionListener(listener);
    }*/

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}