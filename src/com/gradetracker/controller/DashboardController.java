package com.gradetracker.controller;

import com.gradetracker.dao.StudentDAO;
import com.gradetracker.dao.StudentDAOImpl; 
import com.gradetracker.model.Student;
import com.gradetracker.view.AnalysisView;
import com.gradetracker.view.DashboardView;
import com.gradetracker.view.GradeEntryView;
import com.gradetracker.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController implements ActionListener {

    private final DashboardView view;
    private final Student currentStudent;

    public DashboardController(DashboardView view, Student student) {
        this.view = view;
        this.currentStudent = student;
        this.view.addGradeEntryListener(this);
        this.view.addAnalysisListener(this);
        this.view.addLogoutListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getGradeEntryButton()) {
            openGradeEntryScreen();
        } else if (source == view.getAnalysisButton()) {
            openAnalysisScreen();
        } else if (source == view.getLogoutButton()) {
            handleLogout();
        }
    }

    private void openGradeEntryScreen() {
        GradeEntryView gradeEntryView = new GradeEntryView();
        new GradeEntryController(gradeEntryView, currentStudent);
        gradeEntryView.setVisible(true);
    }

    private void openAnalysisScreen() {
        AnalysisView analysisView = new AnalysisView();
        new Analysis(analysisView, currentStudent);
        analysisView.setVisible(true);
    }

    private void handleLogout() {
        view.dispose(); 

        
        LoginView loginView = new LoginView();
        
        StudentDAO studentDAO = new StudentDAOImpl();
        
        new AuthController(loginView, studentDAO);
        loginView.setVisible(true);
    }
}