package com.gradetracker.dao;

import com.gradetracker.model.Grade;
import java.util.Map;

public interface GradeDAO {
    Map<String, Grade> getAllGrades();
}