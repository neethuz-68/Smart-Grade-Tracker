package com.gradetracker.dao;

import com.gradetracker.model.Student; // <-- Added missing import

public interface StudentDAO {

    /**
     * Validates user credentials against the database.
     * @param email The user's email.
     * @param password The user's password.
     * @return A Student object if credentials are valid, null otherwise.
     */
    Student validateUser(String email, String password); // <-- Return type changed to Student

    /**
     * Creates a new student record in the database.
     * @param student The Student object to save.
     * @return true if creation is successful, false otherwise.
     */
    boolean createStudent(Student student);
}