package com.servlet.test.controller;

import com.servlet.test.DAO.StudentRepository;
import com.servlet.test.DAO.StudentRepositoryImpl;
import com.servlet.test.model.Courses;
import com.servlet.test.model.Student;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@WebServlet(urlPatterns = "/add", initParams = {@WebInitParam(name = "dbUrl", value = "jdbc:mysql://pupchik.mysql.tools"),
//@WebInitParam(name = "dbUser", value = "pupchik_ciklumtask2"),
//@WebInitParam(name = "dbPassword", value = "3N~2b-eNj6")})
public class AddStudentServlet extends HttpServlet {

    private Connection connection;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("courses", Courses.values());
        try {
            req.getRequestDispatcher("/add-student.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        // resp.sendRedirect("/add-student.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("first");
        String last = req.getParameter("last");
        int age = Integer.valueOf(req.getParameter("age"));
        Student student = new Student();
        student.setAge(age);
        student.setLastName(last);
        student.setFirstName(first);
        student.setCourse(Courses.valueOf(req.getParameter("course")));

        StudentRepository studentRepository = new StudentRepositoryImpl(connection);
        Student savedStudent = studentRepository.addStudent(student);
        System.out.println(savedStudent);

        resp.sendRedirect("/allStudents");
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database is disconnected.");
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        String dbUrl = context.getInitParameter("dbUrl");
        String dbPassword = context.getInitParameter("dbPassword");
        String dbUser = context.getInitParameter("dbUser");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver is not found");
            e.printStackTrace();
        }
        String url = String.format(dbUrl + "/" + dbUser);
        System.out.println(url);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());
        }
        if (connection != null) {
            System.out.println("Database is connected.");
        } else {
            System.out.println("Failed to make connection!");
        }
        this.connection = connection;
    }
}
