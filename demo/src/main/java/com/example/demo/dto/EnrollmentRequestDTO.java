package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        @NotNull(message = "Enrollment date must not be null")
        LocalDate enrollmentDate,

        @NotNull(message = "Student ID must not be null")
        Integer studentId,

        @NotNull(message = "Course ID must not be null")
        Integer courseId
) {
}
