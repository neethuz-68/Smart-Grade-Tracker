package com.gradetracker.dao;

import com.gradetracker.model.Subject;
import java.util.List;

public interface SubjectDAO {
    /**
     * Retrieves a list of all subjects for a specific semester.
     * @param semesterId The ID of the semester.
     * @return A list of Subject objects, populated with their grade info.
     */
    List<Subject> getSubjectsForSemester(int semesterId);
}