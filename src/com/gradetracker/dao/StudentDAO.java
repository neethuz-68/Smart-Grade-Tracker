package com.gradetracker.dao;

import com.gradetracker.model.Student; 

public interface StudentDAO {

    Student validateUser(String email, String password); 

    boolean createStudent(Student student);
}