package com.servlet.test.controller;


import com.servlet.test.model.Student;
import com.servlet.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllStudentsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AllStudentsServlet.class);

    private StudentService studentService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("All list student servlet is called!");

        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("list-students.jsp").forward(req, resp);

    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
