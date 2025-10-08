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
    private JButton dashboardButton; 
    private JLabel sgpaLabel;
    private JLabel cgpaLabel;

    public GradeEntryView() {
        // --- Frame Setup ---
        setTitle("Enter Semester Grades");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ... (Background Panel with Image code remains the same) ...
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
                    g.setColor(new Color(230, 230, 250));
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

        // --- MODIFICATION: Add Dashboard button to the navbar panel ---
        dashboardButton = new JButton("Dashboard"); // Create the button
        logoutButton = new JButton("Logout");
        // ... (styling for logoutButton)

        JPanel rightNavPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        rightNavPanel.setBackground(Color.WHITE);
        rightNavPanel.add(dashboardButton); // Add the new button to the panel
        rightNavPanel.add(logoutButton);
        navbar.add(rightNavPanel, BorderLayout.EAST);

        // ... (The rest of your code for the contentCard, etc., remains the same) ...
        JPanel contentCard = new JPanel(new GridBagLayout());
        contentCard.setBackground(new Color(255, 255, 255, 220));
        contentCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        semesterNumberField = new JTextField(15);
        semesterNumberField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Enter Semester", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.PLAIN, 14)));
        semesterNumberField.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentCard.add(semesterNumberField, gbc);
        String[] columnNames = {"Subject", "Credit", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        subjectsTable = new JTable(tableModel);
        subjectsTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subjectsTable.setRowHeight(30);
        JTableHeader header = subjectsTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(subjectsTable);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentCard.add(scrollPane, gbc);
        addRowButton = new JButton("Add Subject +");
        addRowButton.addActionListener(e -> tableModel.addRow(new Object[]{"", "", ""}));
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentCard.add(addRowButton, gbc);
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
        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomButtons.setOpaque(false);
        saveButton = new JButton("Save & Calculate");
        viewAnalysisButton = new JButton("View Analysis");
        bottomButtons.add(saveButton);
        bottomButtons.add(viewAnalysisButton);
        gbc.gridy = 4;
        contentCard.add(bottomButtons, gbc);
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);
        centerContainer.add(contentCard, new GridBagConstraints());
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerContainer, BorderLayout.CENTER);
        this.add(background);
    }
    
    // ... (createResultLabel and other methods)
    private JLabel createResultLabel(String title) {
        JLabel label = new JLabel(" -- ", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), title,
                TitledBorder.CENTER, TitledBorder.TOP));
        label.setPreferredSize(new Dimension(150, 80));
        return label;
    }
    public int getSemesterNumber() { try { return Integer.parseInt(semesterNumberField.getText()); } catch (NumberFormatException e) { return -1; }}
    public List<Subject> getSubjectsData() { List<Subject> subjects = new ArrayList<>(); for (int i = 0; i < tableModel.getRowCount(); i++) { String name = (String) tableModel.getValueAt(i, 0); float credits = Float.parseFloat((String) tableModel.getValueAt(i, 1)); String gradeLetter = ((String) tableModel.getValueAt(i, 2)).toUpperCase(); float gradePoint = convertGradeToPoint(gradeLetter); subjects.add(new Subject(0, name, credits, 0, gradeLetter, gradePoint)); } return subjects; }
    public void displayResults(double sgpa, double cgpa) { sgpaLabel.setText(String.format("%.2f", sgpa)); cgpaLabel.setText(String.format("%.2f", cgpa)); }

    // --- Public methods for the Controller ---
    public void addSaveListener(ActionListener listener) { saveButton.addActionListener(listener); }
    public void addViewAnalysisListener(ActionListener listener) { viewAnalysisButton.addActionListener(listener); }
    public void addLogoutListener(ActionListener listener) { logoutButton.addActionListener(listener); }
    
    // --- NEW: Method for the Dashboard button listener ---
    public void addDashboardListener(ActionListener listener) {
        dashboardButton.addActionListener(listener);
    }
    
    public void displayMessage(String message) { JOptionPane.showMessageDialog(this, message); }
    private float convertGradeToPoint(String grade) { switch (grade) { case "O": return 10.0f; case "A+": return 9.0f; case "A": return 8.0f; case "B+": return 7.0f; case "B": return 6.0f; case "C": return 5.0f; case "P": return 4.0f; case "F": return 0.0f; default: return 0.0f; } }
}