package com.example.demo.mapper;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequestDTO dto, Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(dto.enrollmentDate());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollment;
    }

    public EnrollmentResponseDTO toResponse(Enrollment enrollment) {
        return new EnrollmentResponseDTO(
                enrollment.getId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId()
        );
    }
}
