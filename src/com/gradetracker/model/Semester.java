package com.gradetracker.model;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private final int semesterNo;
    private final List<Enrollment> enrollments;

    public Semester(int semesterNo) {
        this.semesterNo = semesterNo;
        this.enrollments = new ArrayList<>();
    }

    public void addEnrollment(Enrollment enrollment) {
        // Optional: Check if the enrollment's semester number matches this semester's number.
        if (enrollment.getSemesterNo() == this.semesterNo) {
            this.enrollments.add(enrollment);
        } else {
            System.err.println("Warning: Attempted to add enrollment from semester "
                + enrollment.getSemesterNo() + " to semester " + this.semesterNo);
        }
    }

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

    public int getSemesterNo() {
        return semesterNo;
    }

    public List<Enrollment> getEnrollments() {
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