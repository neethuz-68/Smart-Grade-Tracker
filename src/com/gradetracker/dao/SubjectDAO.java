package dao;

import java.util.List;
import model.Subject;

public interface SubjectDAO {
    // Fetch a subject by its name
    Subject getSubjectByName(String subjectName);

    // Retrieve all subjects
    List<Subject> getAllSubjects();
}
