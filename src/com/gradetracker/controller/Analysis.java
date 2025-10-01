package com.gradetracker.controller;

import java.util.*;

import com.gradetracker.model.Semester;
import com.gradetracker.model.Subject;

public class Analysis{

    // 1. Calculate GPA for a semester
    public double calculateSemesterGPA(Semester sem) {
        double totalPoints = 0;
        double totalCredits = 0;

        for (Subject sub : sem.getSubjects()) {
            double gradePoint = convertGradeToPoint(sub.getGrade()); // A=10, B=8, etc.
            totalPoints += gradePoint * sub.getCredits();
            totalCredits += sub.getCredits();
        }

        return (totalCredits > 0) ? totalPoints / totalCredits : 0.0;
    }

    // 2. Calculate CGPA across semesters
    public double calculateCGPA(List<Semester> semesters) {
        double totalPoints = 0;
        double totalCredits = 0;

        for (Semester sem : semesters) {
            for (Subject sub : sem.getSubjects()) {
                double gradePoint = convertGradeToPoint(sub.getGrade());
                totalPoints += gradePoint * sub.getCredits();
                totalCredits += sub.getCredits();
            }
        }

        return (totalCredits > 0) ? totalPoints / totalCredits : 0.0;
    }

    // 3. Subject-wise average across all semesters
    public Map<String, Double> getSubjectAverages(List<Semester> semesters) {
        Map<String, List<Integer>> subjectMarks = new HashMap<>();

        for (Semester sem : semesters) {
            for (Subject sub : sem.getSubjects()) {
                subjectMarks.putIfAbsent(sub.getSubjectName(), new ArrayList<>());
                subjectMarks.get(sub.getSubjectName()).add(sub.getMarks());
            }
        }

        // Calculate averages
        Map<String, Double> subjectAverages = new HashMap<>();
        for (String subject : subjectMarks.keySet()) {
            List<Integer> marks = subjectMarks.get(subject);
            double avg = marks.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            subjectAverages.put(subject, avg);
        }

        return subjectAverages;
    }

    // 4. Grade distribution across all semesters
    public Map<String, Integer> getGradeDistribution(List<Semester> semesters) {
        Map<String, Integer> distribution = new HashMap<>();

        for (Semester sem : semesters) {
            for (Subject sub : sem.getSubjects()) {
                String grade = sub.getGrade();
                distribution.put(grade, distribution.getOrDefault(grade, 0) + 1);
            }
        }

        return distribution;
    }

    // Helper: convert grade (A, B, C…) to numeric points
    private double convertGradeToPoint(String grade) {
        switch (grade.toUpperCase()) {
            case "A+": return 10.0;
            case "A":  return 9.0;
            case "B+": return 8.0;
            case "B":  return 7.0;
            case "C":  return 6.0;
            case "D":  return 5.0;
            case "E":  return 4.0;
            default:   return 0.0;
        }
    }
}
