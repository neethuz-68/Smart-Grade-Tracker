package com.gradetracker.model;

/**
 * Represents a single subject, including its name, credits,
 * and the grade achieved.
 */
public class Subject {
    private int subjectId;
    private String subjectName;
    private float credits;      // Changed from int to float
    private int marks;
    private String grade;
    private float gradePoint;   // --- NEW --- Added gradePoint for calculations

    // Constructor updated
    public Subject(int subjectId, String subjectName, float credits, int marks, String grade, float gradePoint) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        this.marks = marks;
        this.grade = grade;
        this.gradePoint = gradePoint;
    }

    // --- NEW --- Getter & Setter for gradePoint
    public float getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(float gradePoint) {
        this.gradePoint = gradePoint;
    }

    // --- Getters & Setters ---
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public float getCredits() { return credits; } // Return type changed
    public void setCredits(float credits) { this.credits = credits; } // Parameter type changed

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return subjectName + " (" + grade + ", Grade Point: " + gradePoint + ", " + credits + " credits)";
    }
}