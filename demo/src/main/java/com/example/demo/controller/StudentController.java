package com.example.demo.controller;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
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

    @PostMapping("/students")
    public StudentResponseDTO createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.saveStudentInDb(studentRequestDTO);
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> findAll() {
        return studentService.findAllStudents();
    }
}
