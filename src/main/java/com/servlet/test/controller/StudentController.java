package com.servlet.test.controller;

import com.servlet.test.model.Courses;
import com.servlet.test.model.Student;
import com.servlet.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping(path = "/add")
    public String createStudent(Model model) {
        logger.debug("Add student form is called!");
        model.addAttribute("courses", Courses.values());
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping(path = "/add")
    public String saveStudent(@ModelAttribute(name = "student")
                              @Validated Student student, BindingResult bindingResult,
                              Model model) {
        logger.debug("Save student form is called!");
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", Courses.values());
            return "add-student";
        }
        Student savedStudent = studentService.createStudent(student);
        logger.debug("Student is saved -> " + savedStudent);
        return "redirect:/allStudents";
    }

    @GetMapping(path = "/allStudents")
    public String allStudents(Model model) {
        logger.debug("All list student is called!");
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list-students";
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
