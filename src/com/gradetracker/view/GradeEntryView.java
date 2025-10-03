package com.gradetracker.view;

import com.gradetracker.model.Subject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GradeEntryView extends JFrame {

    private JTextField semesterNumberField;
    private JTable subjectsTable;
    private DefaultTableModel tableModel;
    private JButton addRowButton;
    private JButton saveButton;
    private JLabel sgpaLabel;
    private JLabel cgpaLabel;

    public GradeEntryView() {
        // --- Frame Setup ---
        setTitle("Enter Semester Grades");
        setExtendedState(JFrame.MAXIMIZED_BOTH);        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the whole app
        setLocationRelativeTo(null);

        // --- Main Panel and Layout ---
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Semester Input ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Semester Number:"), gbc);

        semesterNumberField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(semesterNumberField, gbc);

        // --- Subjects Table ---
        String[] columnNames = {"Subject Name", "Credits", "Grade Letter"};
        tableModel = new DefaultTableModel(columnNames, 0);
        subjectsTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(subjectsTable);
        scrollPane.setPreferredSize(new Dimension(550, 200));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // --- Action Buttons ---
        addRowButton = new JButton("Add Subject");
        addRowButton.addActionListener(e -> tableModel.addRow(new Object[]{"", "", ""}));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(addRowButton, gbc);

        saveButton = new JButton("Save & Calculate");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(saveButton, gbc);

        // --- Results Display ---
        sgpaLabel = new JLabel("SGPA: --");
        sgpaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(sgpaLabel, gbc);

        cgpaLabel = new JLabel("CGPA: --");
        cgpaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(cgpaLabel, gbc);
        
        this.add(panel);
    }

    // --- Public Methods for the Controller ---

    public int getSemesterNumber() {
        try {
            return Integer.parseInt(semesterNumberField.getText());
        } catch (NumberFormatException e) {
            return -1; // Return an invalid number if input is not an integer
        }
    }

    public List<Subject> getSubjectsData() {
        List<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = (String) tableModel.getValueAt(i, 0);
            float credits = Float.parseFloat((String) tableModel.getValueAt(i, 1));
            String gradeLetter = ((String) tableModel.getValueAt(i, 2)).toUpperCase();
            float gradePoint = convertGradeToPoint(gradeLetter);
            
            // Assuming 0 for marks as it's not in this form
            subjects.add(new Subject(0, name, credits, 0, gradeLetter, gradePoint));
        }
        return subjects;
    }

    public void displayResults(double sgpa, double cgpa) {
        sgpaLabel.setText(String.format("This Semester's SGPA: %.2f", sgpa));
        cgpaLabel.setText(String.format("New Overall CGPA: %.2f", cgpa));
    }

    public void addSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Helper method to convert grade letters to points
    private float convertGradeToPoint(String grade) {
        switch (grade) {
            case "O": return 10.0f;
            case "A+": return 9.0f;
            case "A": return 8.0f;
            case "B+": return 7.0f;
            case "B": return 6.0f;
            case "C": return 5.0f;
            case "P": return 4.0f;
            case "F": return 0.0f;
            default: return 0.0f;
        }
    }
}