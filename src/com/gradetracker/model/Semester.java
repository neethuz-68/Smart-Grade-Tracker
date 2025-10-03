package com.gradetracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single academic semester, containing a list of subjects.
 * It includes the logic to calculate the GPA for this specific semester.
 */
public class Semester {
    private int semesterId;
    private int semesterNumber;
    private List<Subject> subjects;

    // --- NEW --- Simpler constructor
    public Semester(int semesterId, int semesterNumber) {
        this.semesterId = semesterId;
        this.semesterNumber = semesterNumber;
        this.subjects = new ArrayList<>(); // Initialize to an empty list
    }

    // Full constructor
    public Semester(int semesterId, int semesterNumber, List<Subject> subjects) {
        this.semesterId = semesterId;
        this.semesterNumber = semesterNumber;
        this.subjects = subjects;
    }

    public double calculateSGPA() {
        if (subjects == null || subjects.isEmpty()) {
            return 0.0;
        }
        double totalPoints = 0.0;
        double totalCredits = 0.0;
        for (Subject subject : subjects) {
            totalPoints += subject.getGradePoint() * subject.getCredits();
            totalCredits += subject.getCredits();
        }
        return (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;
    }

    public double calculateGPA() {
        if (subjects == null || subjects.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        double totalCredits = 0.0;

        for (Subject subject : subjects) {
            totalPoints += subject.getCredits() * subject.getGradePoint();
            totalCredits += subject.getCredits();
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalPoints / totalCredits;
    }
    
    public void addSubject(Subject subject) {
        if (this.subjects == null) {
            this.subjects = new ArrayList<>();
        }
        this.subjects.add(subject);
    }

    // --- Getters & Setters ---
    public int getSemesterId() { return semesterId; }
    public void setSemesterId(int semesterId) { this.semesterId = semesterId; }

    public int getSemesterNumber() { return semesterNumber; }
    public void setSemesterNumber(int semesterNumber) { this.semesterNumber = semesterNumber; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    @Override
    public String toString() {
        return "Semester " + semesterNumber + " with " + (subjects != null ? subjects.size() : 0) + " subjects.";
    }
}