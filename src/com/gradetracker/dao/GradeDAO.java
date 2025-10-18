package com.gradetracker.dao;

import com.gradetracker.model.Grade;
import java.util.Map;

public interface GradeDAO {
    /**
     * Retrieves all grades and their point values from the database.
     * @return A Map where the key is the letter grade (e.g., "A+") and
     * the value is the complete Grade object.
     */
    Map<String, Grade> getAllGrades();
}