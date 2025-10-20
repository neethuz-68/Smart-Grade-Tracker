package com.gradetracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; // TreeMap keeps semesters sorted by number

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
    
    /**
     * Adds a new enrollment. It finds the correct semester or creates a new one
     * if it's the first enrollment for that semester.
     * @param enrollment The enrollment record to add.
     */
    public void addEnrollment(Enrollment enrollment) {
        int semesterNo = enrollment.getSemesterNo();
        
        // Get the semester from the map. If it doesn't exist, create it.
        Semester semester = this.semesters.computeIfAbsent(semesterNo, k -> new Semester(k));
        
        // Add the enrollment to that semester
        semester.addEnrollment(enrollment);
    }
    
    /**
     * Calculates the SGPA for a given semester.
     * This method now DELEGATES the calculation to the specific Semester object.
     * @param semesterNo The semester to calculate the GPA for.
     * @return The calculated SGPA, or 0.0 if the semester is not found.
     */
    public double getSgpa(int semesterNo) {
        Semester semester = semesters.get(semesterNo);
        if (semester != null) {
            return semester.calculateSgpa();
        }
        return 0.0; // Or throw an exception, e.g., IllegalArgumentException
    }
    
    /**
     * Calculates the Cumulative Grade Point Average (CGPA) for the student.
     * This method now aggregates the results from all Semester objects.
     * @return The calculated CGPA.
     */
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

    // --- Getters ---
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