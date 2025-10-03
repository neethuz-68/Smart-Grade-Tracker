package com.gradetracker.dao;

import com.gradetracker.model.Semester;
import java.util.List;

public interface SemesterDAO {
    List<Semester> getAllSemestersForStudent(int studentId);
    boolean saveSemester(int studentId, Semester semester);
}