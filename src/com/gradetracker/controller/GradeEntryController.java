package com.gradetracker.controller;

import com.gradetracker.dao.SemesterDAO;
import com.gradetracker.dao.SubjectDAO;
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
    private SubjectDAO subjectDAO;

    public GradeEntryController(GradeEntryView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        this.semesterDAO = new SemesterDAO(); 
        this.subjectDAO = new SubjectDAO();

        this.view.addSaveListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleSaveAndCalculate();
    }

    private void handleSaveAndCalculate() {
        int semesterNo = view.getSemesterNumber();
        List<Subject> subjects = view.getSubjectsData();

        Semester newSemester = new Semester(0, semesterNo); 
        newSemester.setSubjects(subjects);

        // 3. Save the new semester and its subjects to the database
        // (This assumes you have created the necessary DAO methods)
        boolean success = semesterDAO.saveSemester(currentStudent.getStudentId(), newSemester);
        if (!success) {
            view.displayMessage("Error: Could not save grades to the database.");
            return;
        }

        // 4. Refresh the student's data to include the new semester
        // (This assumes you have a method to reload a student's data)
        currentStudent = semesterDAO.getStudentWithSemesters(currentStudent.getStudentId());

        // 5. Calculate the SGPA for the semester just entered
        double sgpa = newSemester.calculateSGPA();

        // 6. Calculate the new overall CGPA for the student
        double cgpa = currentStudent.calculateCGPA();

        // 7. Display the results back in the View
        view.displayResults(sgpa, cgpa);
    }
}