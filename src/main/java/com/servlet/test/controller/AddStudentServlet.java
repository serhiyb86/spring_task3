package com.servlet.test.controller;

import com.servlet.test.model.Courses;
import com.servlet.test.model.Student;
import com.servlet.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddStudentServlet extends HttpServlet {
    private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(AddStudentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.debug("Add 'get' student servlet is called!");
        req.setAttribute("courses", Courses.values());
        try {
            req.getRequestDispatcher("add-student.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.debug("Add 'post' student servlet is called!");
        String first = req.getParameter("first");
        String last = req.getParameter("last");
        int age = Integer.valueOf(req.getParameter("age"));
        Student student = new Student();
        student.setAge(age);
        student.setLastName(last);
        student.setFirstName(first);
        student.setCourse(Courses.valueOf(req.getParameter("course")));
        Student savedStudent = studentService.createStudent(student);

        logger.debug("Student is saved -> " + savedStudent);

        resp.sendRedirect("allStudents");
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
