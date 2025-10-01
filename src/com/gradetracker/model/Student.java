package com.gradetracker.model;

import java.util.ArrayList; // Import ArrayList
import java.util.List;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String password;
    private List<Semester> semesters;

    // --- NEW --- Simpler constructor for new students or for login
    public Student(int studentId, String name, String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.semesters = new ArrayList<>(); // Initialize to an empty list
    }

    // Full constructor (still useful)
    public Student(int studentId, String name, String email, String password, List<Semester> semesters) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.semesters = semesters;
    }

    // Getters & Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Semester> getSemesters() { return semesters; }
    public void setSemesters(List<Semester> semesters) { this.semesters = semesters; }

    @Override
    public String toString() {
        return "Student{" + "id=" + studentId + ", name='" + name + "', semesters=" + (semesters != null ? semesters.size() : 0) + "}";
    }
}