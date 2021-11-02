package com.servlet.test.DAO;

import com.servlet.test.model.Courses;
import com.servlet.test.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final String INSERT_STUDENT = "INSERT INTO `students`(`LastName`, `FirstName`, `Age`, `course`) " +
            "VALUES (?,?,?,?)";
    private final String SELECT_ALL = "SELECT * FROM `students` ORDER by id";

    private Connection connection;

    public StudentRepositoryImpl() {
        connection = ConnectToMySQL.getConnection();

    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery(SELECT_ALL);
            while (rs.next()) {
                Student student = new Student();
                student.setFirstName(rs.getString(3));
                student.setLastName(rs.getString(2));
                student.setCourse(Courses.valueOf(rs.getString(5)));
                student.setAge(rs.getInt(4));
                student.setId(rs.getInt(1));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student addStudent(Student student) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_STUDENT)) {
            pstmt.setString(1, student.getLastName());
            pstmt.setString(2, student.getFirstName());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getCourse().name());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int anInt = rs.getInt(1);
            student.setId(anInt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }


}
