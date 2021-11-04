package com.servlet.test.service;

import com.servlet.test.DAO.StudentRepository;
import com.servlet.test.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        if (student != null)
            return studentRepository.saveAndFlush(student);
        else throw new NullPointerException("Student can't be null!");
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.isEmpty() ? new ArrayList<>() : students;
    }

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        logger.debug("Student service is initialized!");
        this.studentRepository = studentRepository;
    }
}
