package com.servlet.test.DAO;

import com.servlet.test.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    Student addStudent(Student student);
}
