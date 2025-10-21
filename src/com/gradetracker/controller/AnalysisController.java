package com.gradetracker.controller;

import com.gradetracker.model.Semester;
import com.gradetracker.model.Student;
import java.util.Map;
import java.util.TreeMap;

public class AnalysisController {
    
    public AnalysisController() {}

    public Map<Integer, Double> getSgpaBySemester(Student student) {
        Map<Integer, Double> sgpaMap = new TreeMap<>();
        for (Semester sem : student.getAllSemesters()) {
            sgpaMap.put(sem.getSemesterNo(), sem.calculateSgpa());
        }
        return sgpaMap;
    }
    
    public double getOverallCgpa(Student student) {
        return student.calculateCgpa();
    }
}