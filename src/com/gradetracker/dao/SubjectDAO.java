package com.gradetracker.dao;

import java.util.List;
import com.gradetracker.model.Subject;

public interface SubjectDAO {
    Subject getSubjectByName(String subjectName);

    List<Subject> getAllSubjects();
}
