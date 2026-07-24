package com.example.demo.dto;

import java.time.LocalDate;

public record EnrollmentResponseDTO(
        Integer id,
        LocalDate enrollmentDate,
        Integer studentId,
        Integer courseId
) {
}
