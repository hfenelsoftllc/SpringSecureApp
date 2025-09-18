package com.hfenelsoftllc.springsecureapp.controller;


import com.hfenelsoftllc.springsecureapp.exceptions.StudentNotFoundException;
import com.hfenelsoftllc.springsecureapp.models.Student;
import com.hfenelsoftllc.springsecureapp.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import jakarta.validation.Valid;

import java.net.URI;
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

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return  (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student studentSaved = studentService.Save(student);
        // implement URI location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
