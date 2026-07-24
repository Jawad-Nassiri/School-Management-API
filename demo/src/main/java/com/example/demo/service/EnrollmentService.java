package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public Enrollment saveEnrollmentInDb(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}