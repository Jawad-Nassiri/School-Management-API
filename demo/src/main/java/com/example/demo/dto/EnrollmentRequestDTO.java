package com.example.demo.dto;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        LocalDate enrollmentDate,
        Integer studentId,
        Integer courseId
) {
}
