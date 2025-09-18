package com.hfenelsoftllc.springsecureapp.controller;


import com.hfenelsoftllc.springsecureapp.exceptions.StudentNotFoundException;
import com.hfenelsoftllc.springsecureapp.models.Student;
import com.hfenelsoftllc.springsecureapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new StudentNotFoundException("id :" +id);
        }
        return student;
    }

//    @GetMapping("/students/{name}")
//    public List<Student> getStudentByName(@PathVariable String name) {
//        Student student = studentService.findByName(name);
//        if (student == null) {
//            throw new StudentNotFoundException("name :" +name);
//        }
//        return studentService.findAll();
//    }
}
