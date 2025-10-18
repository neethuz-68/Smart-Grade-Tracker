package com.gradecalculator.models;

/**
 * Represents a single entry from the Grade lookup table.
 * This class is immutable as the grade-to-point mapping is fixed.
 */
public class Grade {

    private final String letterGrade;
    private final float gradePoint;

    /**
     * Constructor for a Grade object.
     * @param letterGrade The letter grade (e.g., "A+", "B").
     * @param gradePoint The corresponding numerical point value (e.g., 9.0, 6.0).
     */
    public Grade(String letterGrade, float gradePoint) {
        this.letterGrade = letterGrade;
        this.gradePoint = gradePoint;
    }

    // --- Getters ---

    public String getLetterGrade() {
        return letterGrade;
    }

    public float getGradePoint() {
        return gradePoint;
    }
}