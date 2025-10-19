package com.gradetracker.model;

public class Grade {

    private final String letterGrade;
    private final float gradePoint;

    public Grade(String letterGrade, float gradePoint) {
        this.letterGrade = letterGrade;
        this.gradePoint = gradePoint;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public float getGradePoint() {
        return gradePoint;
    }
}