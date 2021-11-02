package com.servlet.test.service;

import com.servlet.test.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    List<Student> getAllStudents();
}
