package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudentInDb(student);
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAllStudents();
    }
}
