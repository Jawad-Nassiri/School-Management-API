package com.example.demo.controller;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.entity.Enrollment;
import com.example.demo.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enrollments")
    public EnrollmentResponseDTO createEnrollment(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        return enrollmentService.saveEnrollmentInDb(enrollmentRequestDTO);
    }

    @GetMapping("/enrollments")
    public List<EnrollmentResponseDTO> findAll() {
        return enrollmentService.findAllEnrollments();
    }
}