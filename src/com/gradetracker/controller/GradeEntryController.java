package com.gradetracker.controller;

import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.SemesterDAOImpl;
import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import com.gradetracker.model.Subject;
import com.gradetracker.view.GradeEntryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GradeEntryController implements ActionListener {

    private GradeEntryView view;
    private Student currentStudent;
    private SemesterDAO semesterDAO;

    public GradeEntryController(GradeEntryView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        this.semesterDAO = new SemesterDAOImpl();
        this.view.addSaveListener(this);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        handleSaveAndCalculate();
    } 

    private void handleSaveAndCalculate() {
        int semesterNo = view.getSemesterNumber();
        List<Subject> subjects = view.getSubjectsData();

        if (semesterNo <= 0 || subjects.isEmpty()) {
            view.displayMessage("Please enter a valid semester number and at least one subject.");
            return;
        }

        Semester newSemester = new Semester(0, semesterNo);
        newSemester.setSubjects(subjects);

        boolean success = semesterDAO.saveSemester(currentStudent.getStudentId(), newSemester);
        if (!success) {
            view.displayMessage("Error: Could not save grades to the database.");
            return;
        }

        currentStudent.addSemester(newSemester);

        double sgpa = newSemester.calculateSGPA();
        double cgpa = currentStudent.calculateCGPA();

        view.displayResults(sgpa, cgpa);
        view.displayMessage("Grades saved successfully!");

    }

} 