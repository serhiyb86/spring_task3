package com.servlet.test.controller;

import com.servlet.test.DAO.ConnectToMySQL;
import com.servlet.test.DAO.StudentRepository;
import com.servlet.test.DAO.StudentRepositoryImpl;
import com.servlet.test.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AllStudentsServlet extends HttpServlet {

    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentRepository studentRepository = new StudentRepositoryImpl(connection);
        List<Student> students = studentRepository.getAllStudents();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>List of students</h2>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>");
        writer.println("ID:");
        writer.println("</th>");
        writer.println("<th>");
        writer.println("First name:");
        writer.println("</th>");
        writer.println("<th>");
        writer.println("Last name:");
        writer.println("</th>");
        writer.println("<th>");
        writer.println("Age:");
        writer.println("</th>");
        writer.println("<th>");
        writer.println("Course:");
        writer.println("</th>");
        writer.println("</tr>");
        for (Student student : students) {
            writer.println("<tr>");
            writer.println("<td>");
            writer.println(student.getId());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(student.getFirstName());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(student.getLastName());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(student.getAge());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(student.getCourse());
            writer.println("</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("<p>");
        writer.println("<p><a href=\"/add\">ADD Student to a particular course.</a> </p>");
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
    public void init() {
        connection = ConnectToMySQL.getConnection();
    }
}
