package com.gradetracker.controller;

import com.gradetracker.model.Student;
import com.gradetracker.view.DashboardView;
import com.gradetracker.view.GradeEntryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController implements ActionListener {

    private DashboardView view;
    private Student currentStudent;

    public DashboardController(DashboardView view, Student student) {
        this.view = view;
        this.currentStudent = student;

        this.view.addGradeEntryListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method is called when any listened-to button is clicked
        String command = e.getActionCommand();

        // Check which button was clicked and open the corresponding screen
        if ("Enter / Edit Grades".equals(command)) {
            openGradeEntryScreen();
        }
    }

    private void openGradeEntryScreen() {
        // 1. Create an instance of the GradeEntryView
        GradeEntryView gradeEntryView = new GradeEntryView();
        
        // 2. Create its controller, passing it the view and the logged-in student
        new GradeEntryController(gradeEntryView, currentStudent);
        
        // 3. Make the grade entry screen visible
        gradeEntryView.setVisible(true);
    }
}