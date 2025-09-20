package models;

import java.util.List;

public class Semester {
    private int semesterId;
    private int semesterNumber;
    private List<Subject> subjects;

    // Constructor
    public Semester(int semesterId, int semesterNumber, List<Subject> subjects) {
        this.semesterId = semesterId;
        this.semesterNumber = semesterNumber;
        this.subjects = subjects;
    }

    // Getters & Setters
    public int getSemesterId() { return semesterId; }
    public void setSemesterId(int semesterId) { this.semesterId = semesterId; }

    public int getSemesterNumber() { return semesterNumber; }
    public void setSemesterNumber(int semesterNumber) { this.semesterNumber = semesterNumber; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    @Override
    public String toString() {
        return "Semester " + semesterNumber + " with " + subjects.size() + " subjects.";
    }
}
