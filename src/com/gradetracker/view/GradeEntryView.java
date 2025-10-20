package com.gradetracker.view;

import com.gradetracker.model.Subject;
import com.gradetracker.view.GradeEntryView.SubjectData;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GradeEntryView extends JFrame {

    private JTextField semesterNumberField;
    private JComboBox<Subject> subjectDropdown; 
    private JTextField gradeField;            
    private JButton saveButton;
    private JButton viewAnalysisButton;
    private JButton logoutButton;
    private JButton dashboardButton;
    private JLabel sgpaLabel;
    private JLabel cgpaLabel;

    public record SubjectData(Subject subject, String gradeLetter) {}

    public GradeEntryView() {
        setTitle("Enter Semester Grades");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        URL imageUrl = getClass().getResource("/resources/bg1.jpg");
        ImageIcon bgIcon = (imageUrl != null) ? new ImageIcon(imageUrl) : null;
        Image bgImage = (bgIcon != null) ? bgIcon.getImage() : null;
        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                else { g.setColor(new Color(230, 230, 250)); g.fillRect(0, 0, getWidth(), getHeight()); }
            }
        };
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(0, 70));
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel title = new JLabel("Smart Grade Tracker");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(Color.DARK_GRAY);
        navbar.add(title, BorderLayout.WEST);
        dashboardButton = new JButton("Dashboard");
        logoutButton = new JButton("Logout");
        JPanel rightNavPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        rightNavPanel.setBackground(Color.WHITE);
        rightNavPanel.add(dashboardButton);
        rightNavPanel.add(logoutButton);
        navbar.add(rightNavPanel, BorderLayout.EAST);

        JPanel contentCard = new JPanel(new GridBagLayout());
        contentCard.setBackground(new Color(255, 255, 255, 220));
        contentCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        semesterNumberField = new JTextField(15);
        semesterNumberField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Enter Semester Number"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentCard.add(semesterNumberField, gbc);

        JPanel entryFormPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        entryFormPanel.setOpaque(false);
        
        subjectDropdown = new JComboBox<>();
        subjectDropdown.setPreferredSize(new Dimension(250, 40));
        
        gradeField = new JTextField(5);
        gradeField.setBorder(BorderFactory.createTitledBorder("Grade"));

        saveButton = new JButton("Add & Calculate");

        entryFormPanel.add(new JLabel("Subject:"));
        entryFormPanel.add(subjectDropdown);
        entryFormPanel.add(new JLabel("Grade:"));
        entryFormPanel.add(gradeField);
        entryFormPanel.add(saveButton);

        gbc.gridy = 1;
        contentCard.add(entryFormPanel, gbc);

        JPanel resultsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        resultsPanel.setOpaque(false);
        sgpaLabel = createResultLabel("SGPA");
        cgpaLabel = createResultLabel("Updated CGPA");
        resultsPanel.add(sgpaLabel);
        resultsPanel.add(cgpaLabel);
        gbc.gridy = 2;
        contentCard.add(resultsPanel, gbc);
        
        viewAnalysisButton = new JButton("View Full Analysis");
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        contentCard.add(viewAnalysisButton, gbc);

        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);
        centerContainer.add(contentCard);
        background.add(navbar, BorderLayout.NORTH);
        background.add(centerContainer, BorderLayout.CENTER);
        this.add(background);
    }
    public void populateSubjectDropdown(List<Subject> subjects) {
        subjectDropdown.removeAllItems();
        for (Subject subject : subjects) {
            subjectDropdown.addItem(subject);
        }
    }
    public SubjectData getSingleSubjectData() {
        Subject selectedSubject = (Subject) subjectDropdown.getSelectedItem();
        String gradeLetter = gradeField.getText();
        if (selectedSubject == null || gradeLetter.trim().isEmpty()) {
            return null;
        }
        return new SubjectData(selectedSubject, gradeLetter);
    }

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
    public void displayResults(double sgpa, double cgpa) { sgpaLabel.setText(String.format("%.2f", sgpa)); cgpaLabel.setText(String.format("%.2f", cgpa)); }
    public void addSaveListener(ActionListener listener) { saveButton.addActionListener(listener); }
    public void addViewAnalysisListener(ActionListener listener) { viewAnalysisButton.addActionListener(listener); }
    public void addLogoutListener(ActionListener listener) { logoutButton.addActionListener(listener); }
    public void addDashboardListener(ActionListener listener) { dashboardButton.addActionListener(listener); }
    public void displayMessage(String message) { JOptionPane.showMessageDialog(this, message); }
}