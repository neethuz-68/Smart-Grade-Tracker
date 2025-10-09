// In file: com/gradecalculator/models/Semester.java
package com.gradecalculator.models;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private final int semesterNo;
    private final List<Enrollment> enrollments;

    public Semester(int semesterNo) {
        this.semesterNo = semesterNo;
        this.enrollments = new ArrayList<>();
    }

    /**
     * Adds an enrollment record (a subject taken) to this semester.
     * @param enrollment The enrollment to add.
     */
    public void addEnrollment(Enrollment enrollment) {
        // Optional: Check if the enrollment's semester number matches this semester's number.
        if (enrollment.getSemesterNo() == this.semesterNo) {
            this.enrollments.add(enrollment);
        } else {
            System.err.println("Warning: Attempted to add enrollment from semester "
                + enrollment.getSemesterNo() + " to semester " + this.semesterNo);
        }
    }

    /**
     * Calculates the Semester Grade Point Average (SGPA) for this semester.
     * The logic is now contained entirely within the Semester class.
     * @return The calculated SGPA as a double.
     */
    public double calculateSgpa() {
        if (enrollments.isEmpty()) {
            return 0.0;
        }

        double totalQualityPoints = enrollments.stream()
            .mapToDouble(Enrollment::getQualityPoints)
            .sum();
            
        int totalCredits = enrollments.stream()
            .mapToInt(e -> e.getSubject().getCredit())
            .sum();
            
        if (totalCredits == 0) {
            return 0.0;
        }
        
        return totalQualityPoints / totalCredits;
    }

    // --- Getters ---
    public int getSemesterNo() {
        return semesterNo;
    }

    public List<Enrollment> getEnrollments() {
        // Return a copy to prevent external modification
        return new ArrayList<>(enrollments);
    }
    
    public int getTotalCredits() {
        return enrollments.stream()
            .mapToInt(e -> e.getSubject().getCredit())
            .sum();
    }
    
    public double getTotalQualityPoints() {
         return enrollments.stream()
            .mapToDouble(Enrollment::getQualityPoints)
            .sum();
    }
}