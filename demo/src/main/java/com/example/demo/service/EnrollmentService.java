package com.example.demo.service;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.mapper.EnrollmentMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentResponseDTO saveEnrollmentInDb(EnrollmentRequestDTO enrollmentRequestDTO) {
        Student student = studentRepository.findById(enrollmentRequestDTO.studentId()).get();
        Course course = courseRepository.findById(enrollmentRequestDTO.courseId()).get();

        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequestDTO, student, course);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponse(savedEnrollment);
    }

    public List<EnrollmentResponseDTO> findAllEnrollments() {
        return enrollmentRepository.findAll().stream().map(enrollmentMapper::toResponse).toList();
    }
}