// In file: com/gradecalculator/models/Enrollment.java
package com.gradetracker.model;

public class Enrollment {
    private final int enrollmentId;
    private final int stId;
    private final int semesterNo;
    private final Subject subject;
    private final Grade grade;

    public Enrollment(int enrollmentId, int stId, int semesterNo, Subject subject, Grade grade) {
        this.enrollmentId = enrollmentId;
        this.stId = stId;
        this.semesterNo = semesterNo;
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * Calculates the quality points for this enrollment.
     * Quality Points = Subject Credit * Grade Point
     * @return The calculated quality points as a double.
     */
    public double getQualityPoints() {
        return this.subject.getCredit() * this.grade.getGradePoint();
    }

    // --- Getters ---
    public int getEnrollmentId() {
        return enrollmentId;
    }
    
    public int getStId() {
        return stId;
    }
    
    public int getSemesterNo() {
        return semesterNo;
    }

    public Subject getSubject() {
        return subject;
    }

    public Grade getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return "Enrollment{" +
               "semesterNo=" + semesterNo +
               ", subject=" + subject.getSubjectName() +
               ", grade=" + grade.getLetterGrade() +
               '}';
    }
}