package com.gradetracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Student {
    private final int stId;
    private final String name;
    private final String email;
    private final String password; 
    private final Map<Integer, Semester> semesters;

    public Student(int stId, String name, String email, String password) {
        this.stId = stId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.semesters = new TreeMap<>();
    }
    
    public void addEnrollment(Enrollment enrollment) {
        int semesterNo = enrollment.getSemesterNo();
        
        Semester semester = this.semesters.computeIfAbsent(semesterNo, k -> new Semester(k));
        
        semester.addEnrollment(enrollment);
    }
    
    public double getSgpa(int semesterNo) {
        Semester semester = semesters.get(semesterNo);
        if (semester != null) {
            return semester.calculateSgpa();
        }
        return 0.0; 
    }
    
    public double calculateCgpa() {
        double totalQualityPoints = semesters.values().stream()
            .mapToDouble(Semester::getTotalQualityPoints)
            .sum();

        int totalCredits = semesters.values().stream()
            .mapToInt(Semester::getTotalCredits)
            .sum();
            
        if (totalCredits == 0) {
            return 0.0;
        }
        
        return totalQualityPoints / totalCredits;
    }

    public int getStId() {
        return stId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public List<Semester> getAllSemesters() {
        return new ArrayList<>(semesters.values());
    }
}