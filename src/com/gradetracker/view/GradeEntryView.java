package com.gradetracker.view;

import com.gradetracker.model.Subject;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GradeEntryView extends JFrame {

    private JTextField semesterNumberField;
    private JTable subjectsTable;
    private DefaultTableModel tableModel;
    private JButton addRowButton;
    private JButton saveButton;
    private JButton viewAnalysisButton;
    private JButton logoutButton;
    private JLabel sgpaLabel;
    private JLabel cgpaLabel;

    public GradeEntryView() {
        // --- Frame Setup ---
        setTitle("Enter Semester Grades");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Background Panel with Image ---
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
                    g.setColor(new Color(230, 230, 250)); // Fallback
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // ===== NAVBAR =====
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(0, 70));
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel title = new JLabel("Smart Grade Tracker");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(Color.DARK_GRAY);
        navbar.add(title, BorderLayout.WEST);
        logoutButton = new JButton("Logout");
        // ... styling for logoutButton
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        logoutPanel.setBackground(Color.WHITE);
        logoutPanel.add(logoutButton);
        navbar.add(logoutPanel, BorderLayout.EAST);

        // --- Main Content Card ---
        JPanel contentCard = new JPanel(new GridBagLayout());
        contentCard.setBackground(new Color(255, 255, 255, 220)); // Semi-transparent white
        contentCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Semester Input Field ---
        semesterNumberField = new JTextField(15);
        semesterNumberField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Enter Semester",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.PLAIN, 14)));
        semesterNumberField.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentCard.add(semesterNumberField, gbc);

        // --- Subjects Table ---
        String[] columnNames = {"Subject", "Credit", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        subjectsTable = new JTable(tableModel);
        subjectsTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subjectsTable.setRowHeight(30);
        JTableHeader header = subjectsTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(subjectsTable);
        gbc.gridy = 1;
        gbc.weighty = 1.0; // Allow table to grow vertically
        gbc.fill = GridBagConstraints.BOTH;
        contentCard.add(scrollPane, gbc);

        // --- Add Subject Button ---
        addRowButton = new JButton("Add Subject +");
        addRowButton.addActionListener(e -> tableModel.addRow(new Object[]{"", "", ""}));
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentCard.add(addRowButton, gbc);
        
        // --- Results Display Panel ---
        JPanel resultsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        resultsPanel.setOpaque(false);
        sgpaLabel = createResultLabel("SGPA");
        cgpaLabel = createResultLabel("Updated CGPA");
        resultsPanel.add(sgpaLabel);
        resultsPanel.add(cgpaLabel);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        contentCard.add(resultsPanel, gbc);
        
        // --- Bottom Buttons ---
        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomButtons.setOpaque(false);
        saveButton = new JButton("Save & Calculate");
        viewAnalysisButton = new JButton("View Analysis");
        bottomButtons.add(saveButton);
        bottomButtons.add(viewAnalysisButton);
        gbc.gridy = 4;
        contentCard.add(bottomButtons, gbc);

        // Use a container panel to control the size and position of the contentCard
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);
        centerContainer.add(contentCard, new GridBagConstraints());

        // Add everything to the main background panel
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerContainer, BorderLayout.CENTER);
        this.add(background);
    }
    
    // Helper to create styled result labels
    private JLabel createResultLabel(String title) {
        JLabel label = new JLabel(" -- ", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), title,
                TitledBorder.CENTER, TitledBorder.TOP));
        label.setPreferredSize(new Dimension(150, 80));
        return label;
    }

    // --- Public methods for the Controller ---
    public int getSemesterNumber() { /* ... same as before ... */ return 0;}
    public List<Subject> getSubjectsData() { /* ... same as before ... */ return new ArrayList<>(); }
    
    public void displayResults(double sgpa, double cgpa) {
        sgpaLabel.setText(String.format("%.2f", sgpa));
        cgpaLabel.setText(String.format("%.2f", cgpa));
    }

    public void addSaveListener(ActionListener listener) { saveButton.addActionListener(listener); }
    public void addViewAnalysisListener(ActionListener listener) { viewAnalysisButton.addActionListener(listener); }
    public void addLogoutListener(ActionListener listener) { logoutButton.addActionListener(listener); }
    
    public void displayMessage(String message) { JOptionPane.showMessageDialog(this, message); }
    private float convertGradeToPoint(String grade) { /* ... same as before ... */ return 0.0f; }
}