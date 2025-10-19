package com.gradetracker.dao;

import java.util.List;
import com.gradetracker.model.Subject;

public interface SubjectDAO {
    // Fetch a subject by its name
    Subject getSubjectByName(String subjectName);

    // Retrieve all subjects
    List<Subject> getAllSubjects();
}
